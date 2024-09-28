import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DoctorDashboard extends JFrame {

    public DoctorDashboard() {
        setTitle("Doctor Dashboard");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        super.setVisible(true);

        String[] columnNames = {"Doctor Name & Phone", "Take Appointment"};

        Object[][] data = {
            {"Dr. Md Mirazul Hasan - 01815556667", "Take Appointment"},
            {"Dr. Sanchita Sarker - 01712223334", "Take Appointment"},
            {"Dr. Abdullah Mohammad - 01617777888", "Take Appointment"},
            {"Dr. Sajib Mondal - 01914444555", "Take Appointment"},
            {"Dr. Mir Hossain - 01715555666", "Take Appointment"},
            {"Dr. Prodip Kumar Saha - 01516667777", "Take Appointment"},
            {"Dr. Ratri Mondal - 01813355666", "Take Appointment"},
            {"Dr. Mehedi Hasan - 01718888000", "Take Appointment"},
            {"Dr. Suzana Rahman - 01619999000", "Take Appointment"},
            {"Dr. Nahid Parvez - 01910002222", "Take Appointment"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return column == 1; 
            }
        };

        JTable table = new JTable(model);

        
        table.getColumn("Take Appointment").setCellRenderer(new ButtonRenderer());
        table.getColumn("Take Appointment").setCellEditor(new ButtonEditor(new JCheckBox(), table, this));

        
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));

        
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    
    public void takeAppointment(String doctorName) {
        
        String patientName = JOptionPane.showInputDialog(this, "Enter Patient Name:", "Appointment for " + doctorName,
                JOptionPane.PLAIN_MESSAGE);
        if (patientName != null && !patientName.trim().isEmpty()) {
            String appointmentTime = JOptionPane.showInputDialog(this, "Enter Appointment Time (e.g., 10:00 AM):",
                    "Appointment for " + doctorName, JOptionPane.PLAIN_MESSAGE);

            if (appointmentTime != null && !appointmentTime.trim().isEmpty()) {
                
                JOptionPane.showMessageDialog(this, "Appointment booked for " + patientName + 
                        " with " + doctorName + " at " + appointmentTime, "Appointment Confirmed", 
                        JOptionPane.INFORMATION_MESSAGE);

                
                saveAppointmentDetails(patientName, doctorName, appointmentTime);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid appointment time entered.", 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid patient name entered.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    public void saveAppointmentDetails(String patientName, String doctorName, String appointmentTime) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("appointments.txt", true))) {
            writer.write("Patient Name: " + patientName + ", Doctor: " + doctorName + ", Appointment Time: " + appointmentTime);
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving appointment details.", 
                    "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    
    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean clicked;
        private JTable table;
        private DoctorDashboard parent;

        public ButtonEditor(JCheckBox checkBox, JTable table, DoctorDashboard parent) {
            super(checkBox);
            this.table = table; 
            this.parent = parent;
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            clicked = true;
            return button;
        }

        public Object getCellEditorValue() {
            if (clicked) {
                
                String doctorName = table.getValueAt(table.getSelectedRow(), 0).toString();
                parent.takeAppointment(doctorName.split(" - ")[0]); 
            }
            clicked = false;
            return label;
        }

        public boolean stopCellEditing() {
            clicked = false;
            return super.stopCellEditing();
        }

        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }

}
