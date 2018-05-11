package support;



import java.util.stream.Stream;
import javafx.beans.value.ObservableValue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * 
 * Uses a combobox tooltip as the suggestion for auto complete and updates the
 * combo box itens accordingly <br />
 * It does not work with space, space and escape cause the combobox to hide and
 * clean the filter ... Send me a PR if you want it to work with all characters
 * -> It should be a custom controller - I KNOW!
 * 
 * @author wsiqueir
 *
 * @param <T>
 */
public class ComboBoxAutoComplete<T> {

	private ComboBox<String> cmb;
	String filter = "";
	private ObservableList<String> originalItems;

	public ComboBoxAutoComplete(ComboBox<String> cmb) {
		this.cmb = cmb;
                cmb.setEditable(false);
                
		originalItems = FXCollections.observableArrayList(cmb.getItems());
//		cmb.setTooltip(new Tooltip());
		//cmb.setOnKeyPressed(this::handleOnKeyPressed);
                cmb.setOnKeyPressed(this::handle);
                
	}

	public void handle(KeyEvent e) {
		ObservableList<String> filteredList = FXCollections.observableArrayList();
		KeyCode code = e.getCode();
                if(code == KeyCode.TAB){
                    filter = "";
                    System.out.println(cmb.getSelectionModel().getSelectedItem() + cmb.getValue());
                    cmb.setValue(cmb.getSelectionModel().getSelectedItem());
                }
		if (code.isLetterKey() || code.isDigitKey()) {
			filter += e.getText();
                        cmb.setValue(filter.toUpperCase());
		}
		if (code == KeyCode.BACK_SPACE && filter.length() > 0) {
			filter = filter.substring(0, filter.length() - 1);
			cmb.getItems().setAll(originalItems);
                        cmb.setValue(filter.toUpperCase());
		}
		if (code == KeyCode.ESCAPE) {
			filter = "";
                        cmb.setValue(filter.toUpperCase());
		}
		if (filter.length() < 2) {
			filteredList = originalItems;
		} else {
                        //cmb.setValue(filter);
			Stream<String> itens = cmb.getItems().stream();
			String txtUsr = filter.toUpperCase();
                        cmb.setValue(filter.toUpperCase());
			itens.filter(el -> el.toUpperCase().contains(txtUsr)).forEach(filteredList::add);
                        if(filteredList.size() == 1){
                            cmb.setValue(filteredList.get(0));
                        }
//			cmb.getTooltip().setText(txtUsr);
//			Window stage = cmb.getScene().getWindow();
//			double posX = stage.getX() + cmb.getBoundsInParent().getMinX();
//			double posY = stage.getY() + cmb.getBoundsInParent().getMinY();
//			cmb.getTooltip().show(stage, posX, posY);
			cmb.show();
		}
//              if
                //cmb.setValue(filter.toUpperCase());
		cmb.getItems().setAll(filteredList);
                
	}

}