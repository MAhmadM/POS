/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BusinessLayer.*;
import DAO.ProductDAO;

import javax.lang.model.type.NullType;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ahmad Khakan
 */
public class SalesMenu extends javax.swing.JFrame {

    /**
     * Creates new form SalesMenu
     */

    List<Product> InventoryList ;
    List<Product> SearchProductList ;
    Product SelectedProduct;

    List<Item> CartItemList ;

    Item SelectedCartItem;
    ItemContainer Container = new Order();
    Order order = (Order)Container;
    User u = new User();

    public SalesMenu( User user ) {
        u = user;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        textFieldID = new javax.swing.JTextField();
        btnAddCart = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableResults = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        textfieldname = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        QuantitySpinner = new javax.swing.JSpinner();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        UpdateCartBtn = new javax.swing.JButton();
        ClearCartBtn = new javax.swing.JButton();
        CreateBillBtn = new javax.swing.JButton();
        DelItemBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Code");

        jLabel4.setText("Name");

        btnAddCart.setText("ADD to Cart");
        btnAddCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCartActionPerformed(evt);
            }
        });

        tableResults.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Code", "Name", "Stock", "Price", "Description"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableResults);
        if (tableResults.getColumnModel().getColumnCount() > 0) {
            tableResults.getColumnModel().getColumn(0).setResizable(false);
            tableResults.getColumnModel().getColumn(1).setResizable(false);
            tableResults.getColumnModel().getColumn(2).setResizable(false);
            tableResults.getColumnModel().getColumn(3).setResizable(false);
        }
        //adding all the list of products in the table model
        InventoryList = getProductList();
     //   InventoryList.remove(InventoryList.size()-1);
        SearchProductList = getProductList();
      //  SearchProductList.remove(SearchProductList.size()-1);

        DefaultTableModel model = (DefaultTableModel) tableResults.getModel();
        for (Product product : SearchProductList)
        {
            Object[] row = {
                    product.getCode(),
                    product.getName(),
                    product.getStockQuantity(),
                    product.getPrice(),
                    product.getDescription()
            };
            model.addRow(row);
        }

        tableResults.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tableResults.getSelectedRow();
                    if (selectedRow != -1) {
                        //Selected Product from inventory
                        SelectedProduct =SearchProductList.get(selectedRow);
                        SelectedProduct.display();

                        //Testfields Registeration
                        textfieldname.setText(SelectedProduct.getName());
                        textFieldID.setText(SelectedProduct.getCode());
                        // Row is selected, do something with the selected row index
                         System.out.println("Selected Row Index: " + selectedRow);
                        String code = tableResults.getValueAt(selectedRow, 0).toString();
                        String name = tableResults.getValueAt(selectedRow, 1).toString();
                        System.out.println("Selected Row Data: Code=" + code + ", Name=" + name);
                    }
                }
            }
        });

        //search function of name
        textfieldname.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                model.setRowCount(0);
                model.equals(UpdateInventorySearchListbyName(textfieldname.getText()));
                System.out.println("Key typed: " + e.getKeyChar());
            }
        });



        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 51));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Billing");

        jLabel2.setText("Quanntity");

        QuantitySpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(textfieldname, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(83, 83, 83)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(QuantitySpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel2)
                                                .addComponent(QuantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(textfieldname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(48, Short.MAX_VALUE))
        );



        jTable1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "id", "Product", "Quantity", "Price", "Amount"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        DefaultTableModel CartModel = (DefaultTableModel) jTable1.getModel();
        CartModel.setRowCount(0);
        CartItemList = new ArrayList<>();

        
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = jTable1.getSelectedRow();
                    if (selectedRow != -1) {
                        //Selected Product from inventory
                        SelectedCartItem = CartItemList.get(selectedRow);
                        //SelectedCartItem.display();

                        //Testfields Registeration
                        textfieldname.setText(SelectedCartItem.getProduct().getName());
                        textFieldID.setText(SelectedCartItem.getProduct().getCode());
                        QuantitySpinner.setValue((int)SelectedCartItem.getQuantityOrdered());
                        // Row is selected, do something with the selected row index
                        System.out.println("Selected Row Index: " + selectedRow);
                        String code = jTable1.getValueAt(selectedRow, 0).toString();
                        String name = jTable1.getValueAt(selectedRow, 1).toString();
                        System.out.println("Selected Row Data in Cart: Code=" + code + ", Name=" + name);
                    }
                }
            }
        });



        jScrollPane2.setViewportView(jTable1);

        UpdateCartBtn.setText("Update Cart");
        UpdateCartBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateCartBtnActionPerformed(evt);
            }
        });

        ClearCartBtn.setText("Clear Cart");
        ClearCartBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearCartBtnActionPerformed(evt);
            }
        });

        CreateBillBtn.setText("Create Bill");
        CreateBillBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateBillBtnActionPerformed(evt);
            }
        });

        DelItemBtn.setText("Del Item");
        DelItemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DelItemBtnActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Inventory Search");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Cart");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(37, 37, 37)
                                                .addComponent(textFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(40, 40, 40)
                                                .addComponent(jLabel4)
                                                .addGap(18, 18, 18)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                                                .addComponent(btnAddCart, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(71, 71, 71))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jScrollPane1)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(CreateBillBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(408, 408, 408))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(UpdateCartBtn)
                                                .addGap(18, 18, 18)
                                                .addComponent(ClearCartBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(DelItemBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(130, 130, 130))))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(244, 244, 244)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(205, 205, 205))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(51, 51, 51)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(btnAddCart)
                                                        .addComponent(textFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel1)
                                                        .addComponent(jLabel4)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(UpdateCartBtn)
                                        .addComponent(ClearCartBtn)
                                        .addComponent(DelItemBtn))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CreateBillBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(190, 190, 190))
        );

        pack();
    }// </editor-fold>


    private void btnAddCartActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if(SelectedProduct!=null)
        {
//            if(!CartItemList.isEmpty())
//            {
//                for(Item item: CartItemList)
//                {
//                    if(item.getProduct().getCode().equals(textFieldID.getText()))
//                    {
//                        System.out.println("Already here bhankalore");
//                    }
//                }
//
////                for(Item item : CartItemList)
////                {
////                    Product product = item.getProduct();
////                    if(product.getCode().equals(SelectedProduct.getCode()))
////                    {
////                        int qty = item.getQuantityOrdered();
////                        item.setQuantityOrdered();
////                    }
////                }
//            }
//            else {
                int quantity = (int) QuantitySpinner.getValue();
                if (quantity > 0 && quantity < 1000 && quantity <= SelectedProduct.getStockQuantity()) {
                    Product product = SelectedProduct.copy();
                    //making new Item
                    Item item = new Item();
                    //item.setId(); // yeh Objectid set karni abhi
                    item.setProduct(product);
                    item.setQuantityOrdered(quantity);
                    item.setPrice();
                    //adding to cart Item list
                    CartItemList.add(item);
                    //Setting Cart Model for display
                    DefaultTableModel CartModel = (DefaultTableModel) jTable1.getModel();
                    CartModel.setRowCount(0);
                    for (Item temp : CartItemList) {
                        Product Onfocus = temp.getProduct();
                        Object[] row = {
                                Onfocus.getCode(),
                                Onfocus.getName(),
                                temp.getQuantityOrdered(),
                                Onfocus.getPrice(),
                                temp.getItemPrice(),
                        };
                        CartModel.addRow(row);
                    }
                    textfieldname.setText("");
                    textFieldID.setText("");
                    QuantitySpinner.setValue((int) 1);
                }
//            }

            //create else body to display message dialog for quantity error
        }
    }

    private void ClearCartBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        CartItemList.clear();
        DefaultTableModel CartModel = (DefaultTableModel) jTable1.getModel();
        CartModel.setRowCount(0);
    }

    private void CreateBillBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        for(Item item : CartItemList) {
            order.add(item);
        }
        order.setUser(u);
        showBillGenerationDialog();
        //Container.
    }
    private void UpdateCartBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if(SelectedCartItem!=null)
        {
            int selectedRow = jTable1.getSelectedRow();
            CartItemList.remove(selectedRow);

            int quantity = (int)QuantitySpinner.getValue();
            if(quantity>0 && quantity < 1000 && quantity<=  SelectedCartItem.getProduct().getStockQuantity())
            {
                Product product = SelectedCartItem.getProduct().copy();
                //making new Item
                Item item = new Item();
                //item.setId(); // yeh Objectid set karni abhi
                item.setProduct(product);
                item.setQuantityOrdered(quantity);
                item.setPrice();
                //adding to cart Item list
                CartItemList.add(item);
                //Setting Cart Model for display
                DefaultTableModel CartModel = (DefaultTableModel) jTable1.getModel();
                CartModel.setRowCount(0);
                for (Item temp : CartItemList) {
                    Product Onfocus = temp.getProduct();
                    Object[] row = {
                            Onfocus.getCode(),
                            Onfocus.getName(),
                            temp.getQuantityOrdered(),
                            Onfocus.getPrice(),
                            temp.getItemPrice(),
                    };
                    CartModel.addRow(row);
                }
                textfieldname.setText("");
                textFieldID.setText("");
                QuantitySpinner.setValue((int)1);
            }


            //create else body to display message dialog for quantity error
        }
    }

    private void DelItemBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if(SelectedCartItem!=null && !CartItemList.isEmpty())
        {
            int selectedRow = jTable1.getSelectedRow();
            CartItemList.remove(selectedRow);
            DefaultTableModel CartModel = (DefaultTableModel) jTable1.getModel();
            CartModel.setRowCount(0);
            for (Item temp : CartItemList) {
                Product Onfocus = temp.getProduct();
                Object[] row = {
                        Onfocus.getCode(),
                        Onfocus.getName(),
                        temp.getQuantityOrdered(),
                        Onfocus.getPrice(),
                        temp.getItemPrice(),
                };
                CartModel.addRow(row);
            }
            textfieldname.setText("");
            textFieldID.setText("");
            QuantitySpinner.setValue((int)1);

        }
    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(SalesMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(SalesMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(SalesMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(SalesMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new SalesMenu().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify
    private javax.swing.JButton ClearCartBtn;
    private javax.swing.JButton CreateBillBtn;
    private javax.swing.JButton DelItemBtn;
    private javax.swing.JSpinner QuantitySpinner;
    private javax.swing.JButton UpdateCartBtn;
    private javax.swing.JButton btnAddCart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tableResults;
    private javax.swing.JTextField textFieldID;
    private javax.swing.JTextField textfieldname;

    // End of variables declaration

    private List<Product> getProductList() {

        ProductDAO productDAO = new ProductDAO();
        return productDAO.getAllProducts();
    }
    public DefaultTableModel UpdateInventorySearchListbyName(String Search)
    {

        List<Product> temp = new ArrayList<>();

        for(Product product :InventoryList)
        {
            String p = product.getName().toLowerCase();
            String s = Search.toLowerCase();
            if(p.contains(s))
            {
                temp.add(product);
            }
        }
        if(!temp.isEmpty())
        {
            SearchProductList = temp;
            System.out.println("New List");
        }
        else
        {
            SearchProductList = InventoryList;
            System.out.println("Original List");
        }
        DefaultTableModel model = (DefaultTableModel) tableResults.getModel();
        for (Product product : SearchProductList) {
            Object[] row = {
                    product.getCode(),
                    product.getName(),
                    product.getStockQuantity(),
                    product.getPrice(),
                    product.getDescription()
            };
            model.addRow(row);
        }
        return model;
    }
    private void showBillGenerationDialog() {
        // Create components
        JLabel nameLabel = new JLabel("Customer Name:");
        JTextField nameTextField = new JTextField();
        JButton generateBillButton = new JButton("Generate Bill");

        // Create a panel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(generateBillButton);

        // Create the dialog
        JDialog dialog = new JDialog();
        dialog.setTitle("Generate Bill");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.add(panel);

        // Add ActionListener to the Generate Bill button
        generateBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform the bill generation logic here
                String customerName = nameTextField.getText();

                order.setCustomer(customerName);
                order.setTime();
                order.save();
                InvoiceGenerator.generateInvoice(order);
                // Close the dialog
                dialog.dispose();
            }
        });

        // Make the dialog non-resizable
        dialog.setResizable(false);

        // Set the size of the dialog
        dialog.setSize(300, 150);

        // Center the dialog on the screen
        dialog.setLocationRelativeTo(null);

        // Display the dialog
        dialog.setVisible(true);
    }
}



