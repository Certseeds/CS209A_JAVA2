/*  CS209A_JAVA2 
    Copyright (C) 2020 nanoseeds
    
    CS209A_JAVA2 is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    CS209A_JAVA2 is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
    */
/**
 * @Github: https://github.com/Certseeds/CS209A_JAVA2
 * @Organization: SUSTech
 * @Author: nanoseeds
 * @Date: 2020-05-23 12:57:59
 * @LastEditors : nanoseeds
 */
/*
Given some dates, split them using year date and day with Chinese Characters:
"年""月""日".
Input:
    2019年4月8日星期一
    2019年4月9日星期二
    2019年4月10日星期三
Output:
    2019
    4
    8
    2019
    4
    9
    2019
    4
    10
**/
package com.nanoseeds.week14;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lab14_Q1 {
    public static void main(String[] args) {
        byte[] temp = "10100".getBytes();
        //        2019 年 4 月 8 日星期一
        //String string_pattern_old = "^\\s*(\\w+\\s*)年(\\s*\\w+\\s*)月(\\s*\\w+\\s*)日(\\S+)$";
        String string_pattern = "^\\s*(.+)\\s*年\\s*(.+)\\s*月\\s*(.+)\\s*日(\\S*)$";
        Scanner input = new Scanner(System.in);
        Pattern p = Pattern.compile(string_pattern);
        while (true) {
            String recieve_input = input.nextLine();
            Matcher m = p.matcher(recieve_input);
            if (m.matches()) {
                for (int i = 1; i < 4; i++) {
                    System.out.println(m.group(i));
                }
            } else {
                System.out.println("NO MATCH");
            }
        }

    }
}
