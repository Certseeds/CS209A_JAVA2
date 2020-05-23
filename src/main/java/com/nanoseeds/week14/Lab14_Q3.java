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
 * @Date: 2020-05-23 12:58:17
 * @LastEditors : nanoseeds
 */
/*
3. Suppose you are developing a website, you want to check (validate) if a user has input a
strong password. A strong password must contain capital letters, lowercase letters, special
characters and digitals meanwhile which length should be larger than 8 and smaller than 16.
(special characters: ~!@#$%^&*()_+|<>,.?/:;'[]{}\)
Input:
    1A11111a
    123aBC.,
Output:
    False
    True
* */
//ABCDEFGHG
//abcdfghhdasd
//01244563213
//~!~#$#$%#%%
//ADASDASasfas
//ASDASD3213f
//DASDA4234@@
// all false
//123Abvc.,
// true
package com.nanoseeds.week14;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lab14_Q3 {
    public static void main(String[] args) {
        String string_pattern = "(?=.*[A-Z]+)(?=.*[a-z]+)(?=.*\\d+)(?=.*[\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\_\\+\\|\\<\\>\\,\\.\\?\\/\\:\\;\\'\\[\\]\\{\\}\\\\]+).{8,16}";
        Scanner input = new Scanner(System.in);
        Pattern p = Pattern.compile(string_pattern);
        while (true) {
            String recieve_input = input.nextLine();
            Matcher m = p.matcher(recieve_input);
            if (m.matches()) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        }
    }
}
