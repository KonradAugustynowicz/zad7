import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Menu extends JMenuBar implements ActionListener {
    InputWindow inputWindow = new InputWindow();
    PicturePanel pp;
    Frame frame;

    JMenuItem i1;
    JMenu menu;

    public Menu(PicturePanel pp, Frame frame){
        this.pp = pp;
        this.frame = frame;

        menu = new JMenu("yes");

        i1 = new JMenuItem("Wykrywanie");
        i1.addActionListener(this);

        menu.add(i1);

        this.add(menu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == i1) {
            JOptionPane.showConfirmDialog(null, inputWindow,
                    "Podaj zakresy", JOptionPane.OK_CANCEL_OPTION);
            pp.binaryByInput(
                    Integer.parseInt(inputWindow.r.getText()),
                    Integer.parseInt(inputWindow.g.getText()),
                    Integer.parseInt(inputWindow.b.getText()));
            System.out.println(pp.percentageOfColor());
        }
    }
}
