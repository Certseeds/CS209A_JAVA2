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
package com.nanoseeds.week11;

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
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
    static final String csv_path = "src/main/java/com/nanoseeds/week11/StudentsGrade.csv";
    static final String csv_path2 = "./StudentsGrade.csv";
    static final String db_path = "./StudentDB.sqlite";
    static final String db_path2 = "src/main/java/com/nanoseeds/week11/StudentDB.sqlite";
    static final String class_path = "src/main/java/com/nanoseeds/week11/Class.csv";
    static final SimpleDateFormat ft2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static final int server_port = 23333;
    static Connection con = null;

    public static void main(String[] args) {
        openDB(db_path2);
        create_table();
        if (!get_name_grade(csv_path)) {
            System.out.println("Error on reading student grade");
            return;
        }
        if (!get_name_class(class_path)) {
            System.out.println("Error on reading student class");
            return;
        }
        try {
            recieve();
        } finally {
            closeDB();
        }
    }

    private static void openDB(String dbPath) {
        try {
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
            con.setAutoCommit(false);
            statement = con.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate("DROP TABLE IF EXISTS StudentsGrade");
            statement.executeUpdate("DROP TABLE IF EXISTS Class");
            statement.executeUpdate("DROP TABLE IF EXISTS ConnectLog");
            statement.executeUpdate(
                    "CREATE TABLE StudentsGrade " +
                            "( NAME TEXT PRIMARY KEY  NOT NULL, " +
                            " GRADE INT NOT NULL)");
            statement.executeUpdate(
                    "CREATE TABLE Class " +
                            "( NAME TEXT PRIMARY KEY NOT NULL, " +
                            " CLASS INT NOT NULL)");
            statement.executeUpdate(
                    "CREATE TABLE ConnectLog( " +
                            "CLASS INT NOT NULL," +
                            "querynumber INT NOT NULL," +
                            "connecttime TEXT NOT NULL," +
                            "PRIMARY KEY(CLASS,connecttime))");
            statement.close();
            con.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public static boolean get_name_grade(String path) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(path), StandardCharsets.UTF_8.name()))) {
            con.setAutoCommit(false);
            String SQL = "INSERT INTO StudentsGrade(NAME,GRADE) VALUES(?, ?)";
            PreparedStatement statement = con.prepareStatement(SQL);
            String line = reader.readLine();
            while (null != (line = reader.readLine())) {
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

    public static boolean get_name_class(String path) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(path), StandardCharsets.UTF_8.name()))) {
            con.setAutoCommit(false);
            String SQL = "INSERT INTO Class(NAME,CLASS) VALUES(?, ?)";
            PreparedStatement statement = con.prepareStatement(SQL);
            String line = reader.readLine();
            while (null != (line = reader.readLine())) {
                statement.setString(1, line.split(",")[1]);
                statement.setInt(2, Integer.parseInt(line.split(",")[0]));
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
        try (ServerSocket ss1 = new ServerSocket(server_port)) {
            System.out.println("Server begin");
            while (true) {
                Socket client_socket = ss1.accept();
                Date date = new Date();
                System.out.println(
                        String.format("Accept a connection(Time:%s)", ft2.format(date)));
                new SocketProcessor(client_socket, date).start();
                //System.out.println(Thread.activeCount());
            }
        } catch (IOException e) {
            System.out.println(
                    "Exception caught when trying to listen on port " + server_port + " or listening for a connection");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("12345");
            System.out.println(e.getMessage());
        }
    }


    static class SocketProcessor extends Thread {
        Socket clientSocket;
        int class_id = 0;
        int query_times = 0;
        Date connect_time;
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        SocketProcessor(Socket clientSocket, Date da) {
            this.clientSocket = clientSocket;
            this.connect_time = da;
        }

        @Override
        public void run() {
            try (PrintWriter out =
                         new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in =
                         new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                out.println("Who are you?");
                System.out.println("Reply: Who are you?");
                String line = in.readLine();
                System.out.println("Recevied " + line);
                if (line.split("\\s").length == 1 &&
                        Integer.parseInt(line.split("\\s")[0]) >= 0 &&
                        Integer.parseInt(line.split("\\s")[0]) <= 4) {
                    this.class_id = Integer.parseInt(line.split("\\s")[0]);
                    out.println("OK");
                    System.out.println("Reply:OK");
                } else {
                    out.println("Illegal user");
                    System.out.println("Reply:Illegal user");
                    clientSocket.close();
                    return;
                }
                update_log();
                while (true) {
                    line = in.readLine();
                    System.out.println(String.format("Class %d: Received:%s", this.class_id, line));
                    System.out.println("Command processed");
                    out.println(line_to_result(line));
                    out.println("end\n");
                    update_log();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    this.clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void update_log() throws SQLException {
            con.setAutoCommit(false);
            String sql = "INSERT OR REPLACE INTO ConnectLog(CLASS,querynumber,connecttime) VALUES(?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, this.class_id);
            pst.setInt(2, this.query_times);
            pst.setString(3, this.ft.format(connect_time));
            pst.executeUpdate();
            con.commit();
            this.query_times++;
        }

        public String line_to_result(String line) {
            System.out.println("Received " + line);
            String[] recieve_command = line.split("\\s");
            String back_sentence = "";
            switch (recieve_command[0].toUpperCase()) {
                case "NAME": {
                    back_sentence = get_name_to_grade(line.substring(recieve_command[0].length() + 1));
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
            return back_sentence;
        }

        public String get_name_to_grade(String name) {
            try {
                con.setAutoCommit(false);
                String sql = "";
                PreparedStatement statement;
                sql = "SELECT COUNT(*) FROM " +
                        "    (SELECT s.NAME,s.GRADE,c.CLASS FROM StudentsGrade s INNER JOIN Class c ON s.NAME = c.NAME) sc " +
                        " WHERE sc.NAME = ?";
                statement = con.prepareStatement(sql);
                statement.setString(1, name);
                ResultSet rs1 =
                        statement.executeQuery();
                if (rs1.getInt(1) > 0) {
                    rs1.close();
                    String sql2;
                    PreparedStatement statement2;
                    sql2 = "SELECT * FROM " +
                            "(SELECT s.NAME,s.GRADE,c.CLASS FROM StudentsGrade s INNER JOIN Class c ON s.NAME = c.NAME) sc" +
                            " WHERE sc.NAME = ?";
                    statement2 = con.prepareStatement(sql2);
                    statement2.setString(1, name);
                    ResultSet rs2 =
                            statement2.executeQuery();
                    String will_return = String.valueOf(rs2.getInt("GRADE"));
                    if (this.class_id != 0 &&
                            rs2.getInt("CLASS") != this.class_id) {
                        will_return = "No access right";
                    }
                    rs2.close();
                    con.commit();
                    return will_return;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return "can not find this";
        }

        public String get_grade_range(int begin, int end) {
            StringBuilder will_return = new StringBuilder();
            if (begin > end) {
                int temp = begin;
                begin = end;
                end = temp;
                will_return.append("Please ensure the number order \n");
            }
            try {

                String SQL;
                PreparedStatement pstmt;
                if (0 == this.class_id) {
                    SQL = "select * from" +
                            "(SELECT s.NAME,s.GRADE,c.CLASS FROM StudentsGrade s INNER JOIN Class c ON s.NAME = c.NAME) sc" +
                            " WHERE (GRADE >= ? AND GRADE < ?)";
                    pstmt = con.prepareStatement(SQL);
                    pstmt.setInt(1, begin);
                    pstmt.setInt(2, end);
                } else {
                    SQL = "select * from" +
                            "(SELECT s.NAME,s.GRADE,c.CLASS FROM StudentsGrade s INNER JOIN Class c ON s.NAME = c.NAME) sc" +
                            " WHERE sc.CLASS = ? AND (GRADE >= ? AND GRADE < ?)";
                    pstmt = con.prepareStatement(SQL);
                    pstmt.setInt(1, this.class_id);
                    pstmt.setInt(2, begin);
                    pstmt.setInt(3, end);
                }
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

        public String get_rank_range(int range) {
            StringBuilder will_return = new StringBuilder();
            try {
                String SQL;
                PreparedStatement pstmt;
                if (0 == this.class_id) {
                    SQL = "select * from ( select *, DENSE_RANK() OVER (ORDER BY GRADE DESC) AS orders" +
                            "         from ( SELECT s.NAME, s.GRADE, c.CLASS" +
                            "                  FROM StudentsGrade s INNER JOIN Class c ON s.NAME = c.NAME" +
                            "              ) sc)" +
                            "WHERE orders <= ?";
                    pstmt = con.prepareStatement(SQL);
                    pstmt.setInt(1, range);
                } else {
                    SQL = "select * from ( select *, DENSE_RANK() OVER (ORDER BY GRADE DESC) AS orders" +
                            "         from ( SELECT s.NAME, s.GRADE, c.CLASS" +
                            "                  FROM StudentsGrade s INNER JOIN Class c ON s.NAME = c.NAME" +
                            "              ) sc  WHERE sc.CLASS is ? )" +
                            "WHERE orders <= ?";
                    pstmt = con.prepareStatement(SQL);
                    pstmt.setInt(1, this.class_id);
                    pstmt.setInt(2, range);
                }
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
}
