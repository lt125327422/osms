package com.itecheasy.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.itecheasy.core.system.Shop;
import org.apache.regexp.RE;
import org.apache.regexp.RESyntaxException;

import com.itecheasy.common.dao.Criteria;
import com.itecheasy.common.dao.Order;
import com.itecheasy.common.dao.Restriction;

public class HqlUtils {
	
	public static final String[] EMPTY_STRING_ARRAY = new String[0];

	public static final String ORDER_BY_PATTERN = "ORDER\\s+BY\\s+([^\\s,\\)]+(\\s+ASC|\\s+DESC)?\\s*[,]?\\s*)+";

	public static final String ORDER_BY_SQL_PATTERN = "^(.+)[\\s|\\)]" + ORDER_BY_PATTERN + "(.*)$";
	
	
	
	/**
	 * 把查询的字符串转为统计字符串
	 * 
	 * @param queryString
	 * @return
	 */
	public static String getCountString(String query) {
		if (query == null)
			return query;
		String subQuery = subQuery(query);
		int idx1 = query.toUpperCase().indexOf("SELECT") + 6;
		int idx2 = query.indexOf(subQuery);
		String countStr = (idx1 < idx2) ? buildCountString(query.substring(idx1, idx2)) : buildCountString("");

		String result = "SELECT" + countStr + subQuery;
		Matcher sqlMatcher = Pattern.compile(ORDER_BY_SQL_PATTERN,
				Pattern.CASE_INSENSITIVE).matcher(query);
		if (sqlMatcher.matches()) {
			Matcher rplMatcher = Pattern.compile(ORDER_BY_PATTERN,
					Pattern.CASE_INSENSITIVE).matcher(result);
			return rplMatcher.replaceAll("");
		}

		return result;
	}

	public static String buildCountString(String subQuery) {
		RE regexp;
		try {
			regexp = new RE("[\\s|\\(]DISTINCT[\\s|\\(]", RE.MATCH_CASEINDEPENDENT);
		} catch (RESyntaxException e) {
			throw new RuntimeException(e);
		}
		boolean distinct = regexp.match(subQuery);
		if (!distinct)
			return " COUNT(*) ";

		char[] chars = subQuery.toCharArray();
		char stopFlag = '\0';
		int startIdx = subQuery.toUpperCase().indexOf("DISTINCT") + 8;
		while (startIdx < chars.length) {
			if (chars[startIdx] == ' ') {
				startIdx++;
				while (chars[startIdx] == ' ')
					startIdx++;
				stopFlag = ' ';
				break;
			} else if (chars[startIdx] == '(') {
				startIdx++;
				stopFlag = ')';
				break;
			}
			startIdx = subQuery.substring(startIdx).toUpperCase().indexOf("DISTINCT") + 8;
		}
		StringBuffer countString = new StringBuffer(" COUNT(DISTINCT ");
		for (; startIdx < chars.length; startIdx++) {
			if (chars[startIdx] == stopFlag)
				break;
			countString.append(chars[startIdx]);
		}
		countString.append(") ");

		return countString.toString();
	}
	
	public static String subQuery(String query) {
		RE regexp;
		try {
			regexp = new RE("^(.*\\))?FROM\\(?", RE.MATCH_CASEINDEPENDENT);
		} catch (RESyntaxException e) {
			throw new RuntimeException(e);
		}
		String[] strArray = toStringArray(query, ' ', false);
		
		List<String> greps = new ArrayList<String>();
		for (String str : strArray)
		{
			if(str.toUpperCase().indexOf("FROM")!= -1 ){
				greps.add(str);
			}
		}
		
		String[] froms = regexp.grep(greps.toArray());
		if (froms == null || froms.length == 0)
			return query;

		int index = 0;
		if (froms[0].length() == 4) {
			index = query.indexOf(froms[0]);
		} else if (froms[0].length() <= 6) {
			index = query.indexOf(froms[0]);
			index += froms[0].toUpperCase().indexOf("FROM");
		} else {
			index = query.indexOf(froms[0]);
			index += froms[0].toUpperCase().indexOf(")FROM") + 1;
		}
		return query.substring(index);
	}

	public static String[] toStringArray(String src, char sep, boolean trim) {
		if (src == null)
			return null;
		int length = src.length();
		if (length == 0)
			return EMPTY_STRING_ARRAY;

		int count = countChar(src, sep);
		String[] array = new String[count + 1];
		int begin = 0;
		int end = 0;
		for (int i = 0; i <= count; i++) {
			end = src.indexOf(sep, begin);
			if (end == -1) {
				array[i] = begin == length ? "" : (trim ? src.substring(begin)
						.trim() : src.substring(begin));
				break;
			} else {
				array[i] = trim ? src.substring(begin, end).trim() : src
						.substring(begin, end);
				begin = end + 1;
			}
		}
		return array;
	}

	/**
	 * Return the count of the char in the String if src == null return -1; if
	 * src.length() == 0 return 0;
	 */
	public static int countChar(String src, char c) {
		if (src == null)
			return -1;
		int length = src.length();
		if (length == 0)
			return 0;

		int count = 0;
		int ch = 0;
		for (int i = 0; i < length; i++) {
			ch = src.charAt(i);
			if (ch == c)
				count++;
		}
		return count;
	}
	
	
	private static void processRestriction(StringBuilder sb, Restriction restriction,
			List<Object> values) {
		
		if (restriction.getType() == Restriction.GT) {
			sb.append(restriction.getAttributeName()).append(" > ? ");
			values.add(restriction.getParms()[0]);
		} else if (restriction.getType() == Restriction.GE) {
			sb.append(restriction.getAttributeName()).append(" >= ? ");
			values.add(restriction.getParms()[0]);
		} else if (restriction.getType() == Restriction.LT) {
			sb.append(restriction.getAttributeName()).append(" < ? ");
			values.add(restriction.getParms()[0]);
		} else if (restriction.getType() == Restriction.LE) {
			sb.append(restriction.getAttributeName()).append(" <= ? ");
			values.add(restriction.getParms()[0]);
		} else if (restriction.getType() == Restriction.IS_NULL) {
			sb.append(restriction.getAttributeName()).append(" = null ");
		} else if (restriction.getType() == Restriction.IS_NOT_NULL) {
			sb.append(restriction.getAttributeName()).append(" != null ");
		} else if (restriction.getType() == Restriction.EQ) {
			sb.append(restriction.getAttributeName()).append(" = ? ");
			values.add(restriction.getParms()[0]);
		} else if (restriction.getType() == Restriction.NOT_EQ) {
			sb.append(restriction.getAttributeName()).append(" != ? ");
			values.add(restriction.getParms()[0]);
		} else if (restriction.getType() == Restriction.LIKE) {
			sb.append(restriction.getAttributeName()).append(" like ? ");
			values.add(restriction.getParms()[0]);
		} else if (restriction.getType() == Restriction.NOT_LIKE) {
			sb.append(restriction.getAttributeName()).append(" not like ? ");
			values.add(restriction.getParms()[0]);
		}else if (restriction.getType() == Restriction.JOIN_CONDITION) {
			sb.append(restriction.getAttributeName());
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void generateHqlByCriteria(Criteria criteria, StringBuilder sb,List<Object> values) {
		
		sb.append(" FROM ");
		
		Set<Class> classzes = criteria.getClasszes();
		int i = 0;
		
		for (Class classz : classzes) {
			if (i++ < classzes.size() - 1)
				sb.append(classz.getName()).append(" ").append(
						classz.getSimpleName()).append(" , ");
			else
				sb.append(classz.getName()).append(" ").append(
						classz.getSimpleName()).append(" ");
		}
		boolean removeFirstFlag = true;
		if (criteria.isValid()) {
			sb.append(" where ");
			for (Restriction andRestriction : criteria.getAndRestictions()) {
				if (!removeFirstFlag) {
					sb.append(" and ");
				}
				processRestriction(sb, andRestriction, values);
				removeFirstFlag = false;
			}
			for (Restriction orRestriction : criteria.getOrRestictions()) {
				if (!removeFirstFlag) {
					sb.append(" or ");
				}
				processRestriction(sb, orRestriction, values);
				removeFirstFlag = false;
			}
			for (Criteria orSub : criteria.getOrSubCriterias()) {
				if (!removeFirstFlag) {
					sb.append(" or ");
				}
				removeFirstFlag = false;
				sb.append(" ( ");
				boolean subRemoveFirst = true;
				for (Restriction andRestriction : orSub.getAndRestictions()) {
					if (!subRemoveFirst) {
						sb.append(" and ");
					}
					processRestriction(sb, andRestriction, values);
					subRemoveFirst = false;
				}
				for (Restriction orRestriction : orSub.getOrRestictions()) {
					if (!subRemoveFirst) {
						sb.append(" or ");
					}
					processRestriction(sb, orRestriction, values);
					subRemoveFirst = false;
				}
				sb.append(" ) ");
			}
			for (Criteria orSub : criteria.getAndSubCriterias()) {
				if (!removeFirstFlag) {
					sb.append(" and ");
				}
				removeFirstFlag = false;
				sb.append(" ( ");
				boolean subRemoveFirst = true;
				for (Restriction andRestriction : orSub.getAndRestictions()) {
					if (!subRemoveFirst) {
						sb.append(" and ");
					}
					processRestriction(sb, andRestriction, values);
					subRemoveFirst = false;
				}
				for (Restriction orRestriction : orSub.getOrRestictions()) {
					if (!subRemoveFirst) {
						sb.append(" or ");
					}
					processRestriction(sb, orRestriction, values);
					subRemoveFirst = false;
				}
				sb.append(" ) ");
			}
		}
		List<Order> orders = criteria.getOrders();
		if (orders.size() > 0)
			sb.append(" order by ");
		for (Order order : orders) {
			sb.append(order.getOrderField()).append(" ")
					.append(order.getType());
			if (orders.indexOf(order) < orders.size() - 1)
				sb.append(", ");
		}
	}


	/**
	 * 拼接字符串的hql
	 * @param hql 前部分
	 * @param criList 需要拼接的条件List
	 * @return
	 */
	public static String  jointCriteriaString(String hql,List<String> criList){
		if (criList!=null && criList.size()>0) {
			StringBuilder builder = new StringBuilder();
			for (String cri : criList) {
				builder.append(",'").append(cri).append("'");
			}
			return hql += " in ( " + builder.substring(1).toString() + " )";
		}
		return hql;
	}




	/**
	 * 拼接数字的where in
	 * @param
	 * @param criList
	 * @return
	 */
	public static String  jointCriteriaInt(List<Integer> criList){
		if (CollectionUtils.isNotEmpty(criList)) {
			StringBuilder builder = new StringBuilder();
			for (Integer integer : criList) {
				builder.append(integer);
				builder.append(",");
			}
			return builder.substring(0, builder.length() - 1);
		}
		return null;
	}




//"FROM ReplenishmentOrderPO WHERE shipmentID=? AND shopId=? "

}
