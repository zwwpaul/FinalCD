package rocket.app.view;

import javafx.scene.control.Label;
import java.util.Collections;

import eNums.eAction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketCode.Action;
import rocketData.LoanRequest;
import javafx.scene.control.Button;

public class MortgageController {

	@FXML
	private TextField txtCreditScore;
	@FXML
	private Button Calculate;
	@FXML
	private TextField txtMortgageAmt;
	@FXML
	private TextField txtIncome;
	@FXML
	private TextField txtExpenses;
	@FXML
	private TextField txtHouseCost;

	@FXML
	private ComboBox cmbTerm;

	@FXML
	private Label lblMortgagePayment;

	// I don't know why those options below cannot show in user's view.
	@FXML
	private void InitialBox() {
		ObservableList<String> OptionsList = FXCollections.observableArrayList("15", "30");
		cmbTerm.setValue("Please Select");
		cmbTerm.setItems(OptionsList);
	}

	private TextField txtNew;

	private MainApp mainApp;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	public void btnCalculatePayment(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Message Here...");
		alert.setHeaderText("Look, an Information Dialog");
		alert.setContentText(txtNew.getText());
		alert.showAndWait().ifPresent(rs -> {
			if (rs == ButtonType.OK) {
				System.out.println("Pressed OK.");
			}
		});
	}

	public void HandleLoanRequestDetails(LoanRequest lRequest) {
		// TODO - RocketClient.HandleLoanRequestDetails
		// lRequest is an instance of LoanRequest.
		// after it's returned back from the server, the payment (dPayment)
		// should be calculated.
		// Display dPayment on the form, rounded to two decimal places
		double rate = lRequest.getdRate();
		double payment = Math.abs(lRequest.getdPayment());
		double maxp1 = lRequest.getiIncome() * 0.28;
		double maxp2 = lRequest.getiIncome() * 0.36 - lRequest.getiExpenses();
		double finalpayment = 0;

		if (maxp1 < maxp2) {
			finalpayment = maxp1;
		} else {
			finalpayment = maxp2;
		}

		if (payment > finalpayment) {
			lblMortgagePayment.setText("House Cost too high");
		} else {
			lblMortgagePayment.setText(
					"Monthly Mortgage Payment is: " + String.format("%1$,.2f", Math.abs(lRequest.getdPayment())));
		}

	}

}
