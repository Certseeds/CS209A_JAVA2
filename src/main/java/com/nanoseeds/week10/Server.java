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
 * @Date: 2020-04-21 17:28:24
 * @LastEditors : nanoseeds
 */
package com.nanoseeds.week10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class Server {
    static final String csv_path = "src/main/java/com/nanoseeds/week10/StudentsGrade.csv";
    static final String csv_path2 = "./StudentsGrade.csv";
    static final String db_path = "./StudentDB.sqlite";
    static final String db_path2 = "src/main/java/com/nanoseeds/week10/StudentDB.sqlite";

    static final int server_port = 23333;
    static HashMap<String, Integer> name_grade;
    static Connection con = null;

    public static void main(String[] args) {
        // System.out.println(System.currentTimeMillis());
        openDB(db_path2);
        create_table();
        if (!get_name_grade(csv_path)) {
            System.out.println("Error on reading file");
            return;
        }
        recieve();

    }

    private static void openDB(String dbPath) {
        try {
            // CLASSPATH must be properly set, for instance on
            // a Linux system or a Mac:
            // $ export CLASSPATH=.:sqlite-jdbc-version-number.jar
            // Alternatively, run the program with
            // $ java -cp .:sqlite-jdbc-version-number.jar BasicJDBC
            Class.forName("org.sqlite.JDBC");
        } catch (Exception e) {
            System.err.println("Cannot find the driver.");
            System.exit(1);
        }
        try {
            con = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            con.setAutoCommit(false);
            System.err.println("Successfully connected to the database.");
        } catch (Exception e) {
            System.err.println("openDB" + e.getMessage());
            System.exit(1);
        }
    }

    private static void closeDB() {
        if (con != null) {
            try {
                con.close();
                con = null;
            } catch (Exception e) {
                // Forget about it
            }
        }
    }

    private static void create_table() {
        Statement statement = null;
        try {
            statement = con.createStatement();
            statement.setQueryTimeout(30);
            String sql = "CREATE TABLE StudentsGrade " +
                    "( NAME TEXT NOT NULL, " +
                    " GRADE INT NOT NULL)";
            statement.executeUpdate("DROP TABLE IF EXISTS StudentsGrade");
            statement.executeUpdate(sql);
            statement.close();
            con.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public static boolean get_name_grade(String path) {
        name_grade = new HashMap<String, Integer>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(path), StandardCharsets.UTF_8.name()))) {
            String SQL = "INSERT INTO StudentsGrade(NAME,GRADE) VALUES(?, ?)";
            PreparedStatement statement = con.prepareStatement(SQL);
            String line = reader.readLine();
            while (null != (line = reader.readLine())) {
                //System.out.println(line);
                if (null != name_grade.get(line.split(",")[0])) {
                    return false;
                }
                statement.setString(1, line.split(",")[0]);
                statement.setInt(2, Integer.parseInt(line.split(",")[1]));
                statement.addBatch();
            }
            statement.executeBatch();
            con.commit();
        } catch (FileNotFoundException ffe) {
            System.out.println(path + " not found");
            return false;
        } catch (IOException ioe) {
            System.out.println("IO exception");
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
        } finally {
            closeDB();
        }
    }

    public static int get_name_to_grade(String name) {
        try {
            Statement statement = con.createStatement();
            ResultSet rs1 =
                    statement.executeQuery("select COUNT(*) from StudentsGrade WHERE NAME = " + "\'" + name + "\'");
            if (rs1.getInt(1) > 0) {
                rs1.close();
                Statement statement2 = con.createStatement();
                ResultSet rs2 =
                        statement2.executeQuery("select GRADE from StudentsGrade WHERE NAME = " + "\'" + name + "\'");
                int will_return = rs2.getInt(1);
                rs2.close();
                con.commit();
                return will_return;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    public static String get_grade_range(int begin, int end) {
        StringBuilder will_return = new StringBuilder();
        if (begin > end) {
            int temp = begin;
            begin = end;
            end = temp;
            will_return.append("Please ensure the number order \n");
        }
        try {
            String SQL = "select * from StudentsGrade WHERE GRADE >= ? AND GRADE < ?";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, begin);
            pstmt.setInt(2, end);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                will_return.append(rs.getString("NAME") + "," + rs.getInt("GRADE") + "\n");
            }
            rs.close();
            con.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return will_return.toString();
    }

    public static String get_rank_range(int range) {
        StringBuilder will_return = new StringBuilder();
        try {
            String SQL = "select * from " +
                    "(select *,DENSE_RANK () OVER (ORDER BY GRADE DESC) AS orders" +
                    "                    from StudentsGrade)  where orders <= ?";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, range);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                will_return.append(rs.getString("NAME") + "," + rs.getInt("GRADE") + "\n");
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return will_return.toString();
    }
}
