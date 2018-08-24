/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.itecheasy.desinger_pattern.Decorator.A2;

public class SideBorder extends Border {
    private char borderChar;                        // 表示装饰边框的文字
    public SideBorder(Display display, char ch) {   // 通过构造函数指定Display和边框文字 
        super(display);
        this.borderChar = ch;
    }
    public int getColumns() {                       // 字符数为字符串字符数加上两侧边框字符数 
        return 1 + display.getColumns() + 1;
    }
    public int getRows() {                          // 行数即被装饰物的行数
        return display.getRows();
    }
    public String getRowText(int row) {             // 指定的那一行的字符串为被装饰物的字符串加上两侧的边框的字符 
        return borderChar + display.getRowText(row) + borderChar;
    }
}