import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


public class main extends Application {

	Stage window;
	Scene scene, scenaInput, scenaOutput, chyba;

	private double xOffset = 0;
	private double yOffset = 0;

	public static void main(String[] args) {

		launch(args);

	}

	class customHeader extends HBox {
		public customHeader() {
			Button zavritOkno = new Button("X");
			zavritOkno.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent actionEvent) {
					Platform.exit();
				}
			});
			this.getChildren().addAll(zavritOkno);
		}
	}

	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;
		window.setTitle("Test1");

		window.initStyle(StageStyle.UNDECORATED);
		BorderPane borderPane = new BorderPane();
		ToolBar toolBar = new ToolBar();

		toolBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				window.setX(mouseEvent.getScreenX() - xOffset);
				window.setY(mouseEvent.getScreenY() - yOffset);
			}
		});


		toolBar.getItems().add(new customHeader());
		toolBar.getStyleClass().add("toolBar");
		borderPane.setTop(toolBar);

		VBox layoutInput = new VBox(10);
		layoutInput.setAlignment(Pos.TOP_CENTER);
		final TextField cisloInput = new TextField();
		cisloInput.setPromptText("Zadejte číslo");
		Button buttonInput = new Button("VYPOČÍTAT");
		layoutInput.getChildren().addAll(toolBar, borderPane, cisloInput, buttonInput);
		//----
		scene = new Scene(layoutInput, 450, 300);
		primaryStage.setScene(scene);
		primaryStage.show();


		buttonInput.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				String cisloStr = cisloInput.getText();
				try {
					int cislo = Integer.parseUnsignedInt(cisloStr);

				} catch (NumberFormatException e) {
					VBox layoutChyba = new VBox(20);
					layoutChyba.setAlignment(Pos.CENTER);
					Label labelChyba = new Label("Vstup není kladné číslo");
					Button buttonChyba = new Button("Zkusit znovu");
					layoutChyba.getChildren().addAll(toolBar, borderPane, labelChyba, buttonChyba);
					buttonChyba.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent actionEvent) {
							window.setScene(scene);
						}
					});

					chyba = new Scene(layoutChyba, 450, 300);
					window.setScene(chyba);
				}

			}
		});


	}
}