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
 * @Date: 2020-04-18 17:28:24
 * @LastEditors : nanoseeds
 */
package com.nanoseeds.week09;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Server {
    static final String csv_path2 = "StudentsGrade.csv";
    static final String csv_path = "src/main/java/com/nanoseeds/week09/StudentsGrade.csv";
    static final int server_port = 23333;
    static HashMap<String, Integer> name_grade;

    public static void main(String[] args) {
        // System.out.println(System.currentTimeMillis());
        if (!get_name_grade(csv_path)) {
            System.out.println("Error on reading file");
            return;
        }
        recieve();
    }

    public static boolean get_name_grade(String path) {
        name_grade = new HashMap<String, Integer>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(path), StandardCharsets.UTF_8.name()))) {
            String line = reader.readLine();
            while (null != (line = reader.readLine())) {
                //System.out.println(line);
                if (null != name_grade.get(line.split(",")[0])) {
                    return false;
                }
                name_grade.put(line.split(",")[0],
                        Integer.valueOf(line.split(",")[1]));
            }
            //System.out.println(12);
        } catch (FileNotFoundException ffe) {
            System.out.println(path + " not found");
            return false;
        } catch (IOException ioe) {
            System.out.println("IO exception");
            return false;
        }
        return true;
    }

    public static void recieve() {
        try {
            ServerSocket ss1 = new ServerSocket(server_port);
            System.out.println("Server begin");
            while (true) {
                try (Socket client_socket = ss1.accept();
                     PrintWriter out =
                             new PrintWriter(client_socket.getOutputStream(), true);
                     BufferedReader in =
                             new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
                ) {
                    String line = in.readLine();
                    System.out.println("Received " + line);
                    String[] recieve_command = line.split(" ");
                    String back_sentence = "";
                    switch (recieve_command[0]) {
                        case "NAME": {
                            back_sentence = String.valueOf(get_name_to_grade(line.substring(recieve_command[0].length() + 1)));
                            if ("-1".equals(back_sentence)) {
                                back_sentence = "Can not find this";
                            }
                            break;
                        }
                        case "GRADE": {
                            if (3 == recieve_command.length) {
                                back_sentence = get_grade_range(Integer.parseInt(recieve_command[1]), Integer.parseInt(recieve_command[2]));
                            }
                            break;
                        }
                        case "TOP": {
                            if (2 == recieve_command.length) {
                                back_sentence = get_rank_range(Integer.parseInt(recieve_command[1]));
                            }
                            break;
                        }
                        default: {
                            back_sentence = "Command not found";
                            break;
                        }
                    }
                    System.out.println("Command processed");
                    out.println(back_sentence);
                }
            }
        } catch (IOException ioe) {
            System.out.println("Unknown io exception");
        }
    }

    public static int get_name_to_grade(String name) {
        return name_grade.getOrDefault(name, -1);
    }

    public static String get_grade_range(int begin, int end) {
        StringBuilder will_return = new StringBuilder();
        if (begin > end) {
            int temp = begin;
            begin = end;
            end = temp;
            will_return.append("Please ensure the number order \n");
        }
        for (String key : name_grade.keySet()) {
            if (name_grade.get(key) >= begin && name_grade.get(key) < end) {
                will_return.append(key + "," + name_grade.get(key) + "\n");
            }
        }
        return will_return.toString();
    }

    public static String get_rank_range(int range) {
        StringBuilder will_return = new StringBuilder();
        ArrayList<Integer> scores = new ArrayList<Integer>();
        for (int i : name_grade.values()) {
            if (!scores.contains(i)) {
                scores.add(i);
            }
        }
        scores.sort(Collections.reverseOrder());
        for (int i = 0; i < Math.min(range, scores.size()); i++) {
            for (String key : name_grade.keySet()) {
                if (name_grade.get(key).equals(scores.get(i))) {
                    will_return.append(key + "," + scores.get(i) + "\n");
                    continue;
                }
            }
        }
        return will_return.toString();
    }
}
