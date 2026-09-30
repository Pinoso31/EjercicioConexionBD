package com.example.ejercicioconexionbd.controller;

import com.example.ejercicioconexionbd.conecction.DatabaseConecction;
import com.example.ejercicioconexionbd.model.Libro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LibroController {
    @FXML
    private TextField txtTituloLibro;

    @FXML
    private TextField txtAutorLibro;

    @FXML
    private TextField txtPrecioLibro;

    @FXML
    private TextField txtStockLibro;

    @FXML
    private ComboBox<String> cmbCategoriaLibro;

    @FXML
    private TableView<Libro> tblLibro;

    @FXML
    private TableColumn<Libro,Integer> colIdLibro;

    @FXML
    private TableColumn<Libro,String> colTituloLibro;

    @FXML
    private TableColumn<Libro, String> colCategoriaLibro;

    @FXML
    private TableColumn<Libro,Double> colPrecioLibro;

    @FXML
    private TableColumn <Libro, Integer > colStockLibr;

    private final ObservableList<Libro> listaLibros = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws SQLException {
        configurarTabla();
        configurarComboBox();
        cargarLibros();
    }

    private void configurarTabla() {
        colIdLibro.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTituloLibro.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        colCategoriaLibro.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colPrecioLibro.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colStockLibro.setCellValueFactory(new PropertyValueFactory<>("stock"));
    }


    // Método encargado de cargar los datos por defecto del combobox.
    private void configurarComboBox() {
        cmbCategoriaLibro.getItems().clear();
        cmbCategoriaLibro.getItems().addAll("Tecnología", "Programación", "Ciencia ficción","Ficcion", "Otros");
    }

    @FXML
    private void cargarLibros() throws SQLException {
        listaLibros.clear();
        String sql = "SELECT * FROM libro";

        try(
                Connection connection = DatabaseConecction.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                ) {

            while (resultSet.next()) {
                Libro libro = new Libro();
                libro.setId(resultSet.getInt("id"));
                libro.setTitulo(resultSet.getString("titulo"));
                libro.setAutor(resultSet.getString("autor"));
                libro.setCategoria(resultSet.getString("categoria"));
                libro.setPrecio(resultSet.getDouble("precio"));
                libro.setStock(resultSet.getInt("stock"));
                listaLibros.add(libro);

            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void guardarLibro() {
        if (!validarCampos()) {
            return;
        }

        String sql = "INSERT INTO libro (titulo, autor, categoria, precio, stock) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (
                Connection connection = DatabaseConecction.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, txtTituloLibro.getText());
            statement.setString(2, txtAutorLibro.getText());
            statement.setString(3, cmbCategoriaLibro.getValue());
            statement.setDouble(3, Double.parseDouble(txtPrecioLibro.getText()));
            statement.setInt(5, Integer.parseInt(txtStockLibro.getText()));
            statement.execute();
            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Registro almacenado",
                    "Libro registrado",
                    "El libro se ha almacenado exitosamente"
            );
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String encabezado, String mensaje){
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private boolean validarCampos() {
        return true;
    }
}
