/* CS209A_JAVA2/week08
   Copyright (C) 2020  nanoseeds

   CS209A_JAVA2/week08 is free software: you can redistribute it and/or modify
   it under the terms of the GNU Affero General Public License as
   published by the Free Software Foundation, either version 3 of the
   License, or (at your option) any later version.

   CS209A_JAVA2/week08 is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU Affero General Public License for more details.

   You should have received a copy of the GNU Affero General Public License
   along with this program.  If not, see <https://www.gnu.org/licenses/>.
   */
/**
 * @Github: https://github.com/Certseeds/CS209A_JAVA2/week08
 * @Organization: SUSTech
 * @Author: nanoseeds
 * @Date: 2020-04-06 19:10:49
 * @LastEditors : nanoseeds
 */
package week08;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import week08.MainApp;
import week08.grid_n_m_gene;

import java.io.File;

public class MainUIOfLifeControl {

    static int[][] blockPattern = {
            {0, 0, 0, 0},
            {0, 1, 1, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0},
    };
    static int[][] beePattern = {
            {0, 1, 1, 0},
            {1, 0, 0, 1},
            {0, 1, 1, 0}
    };
    static int[][] blinkerPattern = {
            {0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0}
    };
    static int[][] gliderPattern = {
            {0, 0, 1},
            {1, 0, 1},
            {0, 1, 1}
    };
    //    static int[][] grid;
//    static int generation = 0;
//    static int M = 10;
//    static int N = 10;
    static grid_n_m_gene g_n_m_g = new grid_n_m_gene();
    @FXML
    TextField gene_number;
    @FXML
    TextArea grid_text_area;
    @FXML
    TextArea test2;
    @FXML
    Button play_button;
    @FXML
    Button BlockButton;
    @FXML
    Button GliderButton;
    @FXML
    Button BlinkerButton;
    private MainApp mainApp;

    @FXML
    private Stage primaryStage;
    @FXML
    private BorderPane rootLayout;

    @FXML
    public static void init(int[][] pattern, int M, int N) {
        if (0 == pattern.length ||
                0 == pattern[0].length ||
                M <= 0 || N <= 0) {
            return;
        }
        g_n_m_g = new grid_n_m_gene(10, 10, 0);
        int left = (M - pattern.length) / 2;
        int down = (N - pattern[0].length) / 2;
        for (int i = 0; i < pattern.length; i++) {
            System.arraycopy(pattern[i], 0, g_n_m_g.getGrid()[left + i], down, pattern[0].length);
        }
        int generation = 0;
        //displayGrid(grid, M, N);

        System.out.printf("Generation: %d\n", generation);
        // grid = nextGeneration(grid, M, N);
        //displayGrid(grid, M, N);
        //generate the nextGeneration and display

    }

    static void displayGrid(int[][] grid, int M, int N) {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 0) {
                    System.out.print(".");
                } else {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    static String grid_to_string() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < g_n_m_g.getM(); i++) {
            for (int j = 0; j < g_n_m_g.getN(); j++) {
                if (g_n_m_g.getGrid()[i][j] == 0) {
                    sb.append(".");
                } else {
                    sb.append("*");
                }
            }
            sb.append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }

    static int[][] nextGeneration(int[][] grid, int M, int N) {
        int[][] future = new int[M][N];

        // Loop through every cell
        for (int l = 1; l < M - 1; l++) {
            for (int m = 1; m < N - 1; m++) {
                // finding no Of Neighbours that are alive
                int aliveNeighbours = 0;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        aliveNeighbours += grid[l + i][m + j];
                    }
                }
                // The cell needs to be subtracted from
                // its neighbours as it was counted before
                aliveNeighbours -= grid[l][m];

                // Implementing the Rules of Life
                // Cell is lonely and dies
                if ((grid[l][m] == 1) && (aliveNeighbours < 2)) {
                    future[l][m] = 0;
                }
                // Cell dies due to over population
                else if ((grid[l][m] == 1) && (aliveNeighbours > 3)) {
                    future[l][m] = 0;
                }
                // A new cell is born
                else if ((grid[l][m] == 0) && (aliveNeighbours == 3)) {
                    future[l][m] = 1;
                }
                // Remains the same
                else {
                    future[l][m] = grid[l][m];
                }
            }
        }
        g_n_m_g.setGeneration(g_n_m_g.getGeneration() + 1);
        return future;
    }

    @FXML
    public void open(File file) {

    }

    @FXML
    public void play_click() {
        System.out.printf("Click Play,Generatio is %d\n", g_n_m_g.getGeneration());
        g_n_m_g.setGrid(nextGeneration(g_n_m_g.getGrid(), g_n_m_g.getM(), g_n_m_g.getN()));
        gene_number.setText(Integer.toString(g_n_m_g.getGeneration()));
        grid_text_area.setText(grid_to_string());
    }

    @FXML
    private void initialize() {
        blockPattern_press();
    }

    @FXML
    private void blockPattern_press() {
        System.out.println("initial with blockPattern");
        initialize_public(blockPattern);
    }

    @FXML
    private void blinkerPattern_press() {
        System.out.println("initial with blinkerPattern");
        initialize_public(blinkerPattern);
    }

    @FXML
    private void gliderPattern_press() {
        System.out.println("initial with gliderPattern");
        initialize_public(gliderPattern);
    }

    @FXML
    public void initialize_public(int[][] Pattern) {
        g_n_m_g.setGeneration(0);
        init(Pattern, g_n_m_g.getM(), g_n_m_g.getN());
        gene_number.setText(Integer.toString(g_n_m_g.getGeneration()));
        grid_text_area.setText(grid_to_string());
    }

    @FXML
    public void update() {
        gene_number.setText(Integer.toString(g_n_m_g.getGeneration()));
        grid_text_area.setText(grid_to_string());
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleOpen() {
        System.out.println("open");
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            mainApp.loadGnmgDataFromFile(file);
        }
        System.out.println(g_n_m_g.getGeneration());
        play_click();
    }

    @FXML
    private void handleSave() {
        File personFile = mainApp.getgnmgFilePath();
        if (personFile != null) {
            mainApp.saveGnmgDataToFile(personFile);
        } else {
            handleSaveAs();
        }
    }

    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mainApp.saveGnmgDataToFile(file);
        }
    }
}
