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
 * @Date: 2020-05-23 12:58:40
 * @LastEditors : nanoseeds
 */
package com.nanoseeds.week14;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
* Suppose you are working for an express company, you accept a new requirement. You
need to automatically extract province, city, district, street, zip code, telephone number
and other information from user input address information
Input:
    广东省深圳市南山区学苑大道南山智园 A7 栋，518055，13422221111，赵老师收
Output:
    Province: 广东
    City: 深圳
    District: 南山
    Street: 学苑大道
    Zip code: 518055
    Telephone number: 13422221111
* */
public class Lab14_Q42 {
    public static void main(String[] args) {
        String string_pattern = "^\\s*(.+)\\s*省\\s*(.+)\\s*市\\s*(.+)\\s*区\\w*(.+[道路街]).+(\\d{6}).+([1]([3-9])[0-9]{9}).*$";
        Scanner input = new Scanner(System.in);
        Pattern p = Pattern.compile(string_pattern);
        while (true) {
            String recieve_input = input.nextLine();
            Matcher m = p.matcher(recieve_input);
            if (m.find()) {
                System.out.println("Province: " + m.group(1));
                System.out.println("City: " + m.group(2));
                System.out.println("District: " + m.group(3));
                System.out.println("Street: " + m.group(4));
                System.out.println("Zip code: " + m.group(5));
                System.out.println("Telephone number: " + m.group(6));
            } else {
                System.out.println("NO MATCH");
            }
        }
    }
}
