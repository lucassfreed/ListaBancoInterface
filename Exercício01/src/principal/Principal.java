package principal;

import java.awt.EventQueue;
import view.AlunoPrincipal;

public class Principal {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AlunoPrincipal();
            }
        });
    }
}
