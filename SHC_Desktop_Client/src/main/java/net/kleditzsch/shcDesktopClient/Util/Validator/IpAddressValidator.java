package net.kleditzsch.shcDesktopClient.Util.Validator;

import javafx.scene.control.Control;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.Validator;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Validiert IP Adressen
 *
 * @author Oliver Kleditzsch
 * @copyright Copyright (c) 2016, Oliver Kleditzsch
 * @license http://opensource.org/licenses/gpl-license.php GNU Public License
 */
public class IpAddressValidator implements Validator<String> {

    /**
     * Applies this function to the given arguments.
     *
     * @param control the first function argument
     * @param s       the second function argument
     * @return the function result
     */
    @Override
    public ValidationResult apply(Control control, String s) {

        String PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";
        if(s == null) {

            s = "";
        }
        return ValidationResult.fromMessageIf( control, "Ungültige IP Adresse", Severity.ERROR, !s.matches(PATTERN));
    }
}
