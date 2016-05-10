package rocket.app;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.function.Consumer;

import javax.swing.SwingUtilities;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import netgame.common.Client;
import rocket.app.view.RocketMainController;
import rocketServer.RocketHub;

public class MainApp extends Application {

	private Stage primaryStage;
	private RocketHub rHub = null;
	private RocketClient rClient = null;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void init() throws Exception {
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// START is executed by the Application framework after INIT
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 1300, 500);

		this.primaryStage = primaryStage;
		
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();

		primaryStage.setX(bounds.getMinX());
		primaryStage.setY(bounds.getMinY());
		primaryStage.setWidth(bounds.getWidth());
		primaryStage.setHeight(bounds.getHeight());
		
		this.primaryStage.setTitle("Rocket");
		this.primaryStage.setScene(scene);
		this.primaryStage.show();
		
		showRocketMenu();
		
	}
	
	public void showRocketMenu() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RocketMain.fxml"));
			BorderPane ClientServerOverview = (BorderPane) loader.load();

			Scene scene = new Scene(ClientServerOverview);

			primaryStage.setScene(scene);

			// Give the controller access to the main app.
			RocketMainController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void EndPoker() {
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent t) {
				Platform.exit();
				System.exit(0);
			}
		});
	}

	@Override
	public void stop() throws Exception {
	}

	public void messageSend(final Object message) {
		rClient.messageSend(message);
	}

	private class RocketClient extends Client {

		public RocketClient(String hubHostName, int hubPort) throws IOException {
			super(hubHostName, hubPort);
		}

		protected void messageSend(Object message) {
			resetOutput();
			super.send(message);
		}

		@Override
		protected void messageReceived(final Object message) {
			Platform.runLater(() -> {
				if (message instanceof String) {
				} else if (message instanceof Object) {
				}
			});
		}

		@Override
		protected void serverShutdown(String message) {

			Platform.runLater(() -> {
				Platform.exit();
				System.exit(0);
			});
		}

	}
}
