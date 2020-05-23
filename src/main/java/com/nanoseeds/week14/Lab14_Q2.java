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
 * @Date: 2020-05-23 12:58:05
 * @LastEditors : nanoseeds
 */
/*
2. Given some email addresses of SUSTech students, use regular expressions to get the
student id and print it:
Input:
    11710001@mail.sustc.edu.cn
    11810002@mail.sustech.edu.cn
    11610003@mail.sustc.edu.cn
Output:
    11710001
    11810002
    11610003
* */
package com.nanoseeds.week14;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lab14_Q2 {
    public static void main(String[] args) {
        String string_pattern = "^(\\d{8})\\@mail.sust[e]?c[h]?.edu.cn$";
        Scanner input = new Scanner(System.in);
        Pattern p = Pattern.compile(string_pattern);
        while (true) {
            String recieve_input = input.nextLine();
            Matcher m = p.matcher(recieve_input);
            if (m.matches()) {
                System.out.println(m.group(1));
            } else {
                System.out.println("NO MATCH");
            }
        }
    }
}
