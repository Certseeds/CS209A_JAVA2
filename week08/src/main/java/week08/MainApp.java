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

import com.sun.tools.javac.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

public class MainApp extends Application {
    static int[][] grid;
    static int[][] blockPattern = {
            {0, 0, 0, 0},
            {0, 1, 1, 0}
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
    static int generation = 0;
    static int M = 10;
    static int N = 10;
    static grid_n_m_gene gnmg_main;
    private Stage primaryStage;
    private BorderPane rootLayout;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");
        initial_MainUI();
    }

    public void initial_MainUI() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/week08/MainUIOfLife.fxml"));
            rootLayout = (BorderPane) loader.load();
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            MainUIOfLifeControl controller = loader.getController();

            controller.setMainApp(this);
            primaryStage.show();
            File file = getgnmgFilePath();
            if (null != file) {
                loadGnmgDataFromFile(file);
                controller.update();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getgnmgFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);

        if (filePath != null) {
            File will_return = new File(filePath);
            if (will_return.exists()) {
                return will_return;
            }
            return null;
        } else {
            return null;
        }
    }

    public void setGnmgFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());
            // Update the stage title.
            primaryStage.setTitle("CS209A_Week08 - " + file.getName());
        } else {
            prefs.remove("filePath");
            // Update the stage title.
            primaryStage.setTitle("CS209A_Week08");
        }
    }

    public void loadGnmgDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(grid_n_m_gene_warpper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            grid_n_m_gene_warpper wrapper = (grid_n_m_gene_warpper) um.unmarshal(file);
            MainUIOfLifeControl.g_n_m_g = wrapper.getGrid_n_m_gene();
//            personData.clear();
//            personData.addAll(wrapper.getPersons());
            // Save the file path to the registry.
            setGnmgFilePath(file);

        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());
            alert.showAndWait();
            setGnmgFilePath(null);
        }
    }

    public void saveGnmgDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(grid_n_m_gene_warpper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            grid_n_m_gene_warpper wrapper = new grid_n_m_gene_warpper();
            wrapper.setGrid_n_m_gene(MainUIOfLifeControl.g_n_m_g);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, file);
            // Save the file path to the registry.
            setGnmgFilePath(file);
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());
            alert.showAndWait();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

}
