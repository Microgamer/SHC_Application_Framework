package net.kleditzsch.shcDesktopClient.Util.Validator;

import javafx.scene.control.Control;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.Validator;

/**
 * Validiert MAC Adressen
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class MacAddressValidator implements Validator<String> {

    /**
     * Applies this function to the given arguments.
     *
     * @param control the first function argument
     * @param s       the second function argument
     * @return the function result
     */
    @Override
    public ValidationResult apply(Control control, String s) {

        String PATTERN = "^[\\da-fA-F]{2}:[\\da-fA-F]{2}:[\\da-fA-F]{2}:[\\da-fA-F]{2}:[\\da-fA-F]{2}:[\\da-fA-F]{2}$";
        if(s == null) {

            s = "";
        }
        return ValidationResult.fromMessageIf( control, "Ungültige MAC Adresse", Severity.ERROR, !s.matches(PATTERN));
    }
}
