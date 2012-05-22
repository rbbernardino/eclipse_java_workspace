package fronteira.principal;

import java.io.File;
import javax.swing.filechooser.*;

import fronteira.principal.Utils;

/* ImageFilter.java is used by FileChooserDemo2.java. */
public class DotFilter extends FileFilter {

    //Accept all directories and all gif, jpg, tiff, or png files.
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = Utils.getExtension(f);
        if (extension != null) {
            if (extension.equals(Utils.dot)) {
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }

    //The description of this filter
    public String getDescription() {
        return "Arquivos Graphviz (*.dot)";
    }
}
