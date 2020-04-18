/*
 *     CS209A_JAVA2
 *     Copyright (C) 2020 nanoseeds
 *
 *     CS209A_JAVA2 is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as
 *     published by the Free Software Foundation, either version 3 of the
 *     License, or (at your option) any later version.
 *
 *     CS209A_JAVA2 is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
/**
 * @Github: https://github.com/Certseeds/CS209A_JAVA2
 * @Organization: SUSTech
 * @Author: nanoseeds
 * @Date: 2020-04-18 17:28:18
 * @LastEditors : nanoseeds
 */
package com.nanoseeds.week09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    static final String ip = "127.0.0.1";
    static final int client_port = 23333;

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Please send a command");
            String command = input.nextLine();
            try (Socket s = new Socket(ip, client_port);
                 PrintWriter out =
                         new PrintWriter(s.getOutputStream(), true);
                 BufferedReader in =
                         new BufferedReader(new InputStreamReader(s.getInputStream()));
            ) {
                out.println(command);
                String line;
                while (null != (line = in.readLine())) {
                    System.out.println(line);
                }
            }
        }
    }
}
