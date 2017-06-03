/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicest.reusables.dialogue.editor;

import com.epicest.reusables.dialogue.DialogueNode;
import com.epicest.reusables.dialogue.DialogueTree;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;

/**
 *
 * @author mjspr
 */
public class TreeEditor extends javax.swing.JFrame {

 public static final int GENERATION_JAVA = 0;
 public static final int GENERATION_JAVASCRIPT = 1;

 protected int generation = GENERATION_JAVA;

 File projectRoot;

 DialogueTree tree = new DialogueTree();

 HashMap<String, DialogueNode> nodes = new HashMap<String, DialogueNode>();

 /**
  * Creates new form TreeEditor
  */
 public TreeEditor() {
  DialogueNode root = new DialogueNode();
  nodes.put("RootNode", root);
  tree.setRoot(root);
  initComponents();
  setMinimumSize(getSize());
  setProjectRoot(new File("Project"));
  refreshLists();
  setLocationRelativeTo(null);
 }

 /**
  * This method is called from within the constructor to initialize the form.
  * WARNING: Do NOT modify this code. The content of this method is always
  * regenerated by the Form Editor.
  */
 @SuppressWarnings("unchecked")
 // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
 private void initComponents() {

  generateGroup = new javax.swing.ButtonGroup();
  generateJava = new javax.swing.JRadioButton();
  generateJavaScript = new javax.swing.JRadioButton();
  generateLabel = new javax.swing.JLabel();
  separator1 = new javax.swing.JSeparator();
  directoryLabel = new javax.swing.JLabel();
  directoryTextField = new javax.swing.JTextField();
  directoryButton = new javax.swing.JButton();
  separator2 = new javax.swing.JSeparator();
  rootNodeLabel = new javax.swing.JLabel();
  rootComboBox = new javax.swing.JComboBox<>();
  separator3 = new javax.swing.JSeparator();
  createButton = new javax.swing.JButton();
  deleteButton = new javax.swing.JButton();
  editButton = new javax.swing.JButton();
  nodeScrollPane = new javax.swing.JScrollPane();
  nodeList = new javax.swing.JList<>();
  separator4 = new javax.swing.JSeparator();
  saveButton = new javax.swing.JButton();

  setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
  setTitle("Dialogue Tree Editor");
  setMinimumSize(null);

  generateGroup.add(generateJava);
  generateJava.setSelected(true);
  generateJava.setText("Java");
  generateJava.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    generateJavaActionPerformed(evt);
   }
  });

  generateGroup.add(generateJavaScript);
  generateJavaScript.setText("Java Script");
  generateJavaScript.setEnabled(false);
  generateJavaScript.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    generateJavaScriptActionPerformed(evt);
   }
  });

  generateLabel.setText("Generate code in:");

  directoryLabel.setText("Generate in directory:");

  directoryTextField.setToolTipText("Directory path");
  directoryTextField.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    directoryTextFieldActionPerformed(evt);
   }
  });

  directoryButton.setText("Choose Directory");
  directoryButton.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    directoryButtonActionPerformed(evt);
   }
  });

  rootNodeLabel.setText("Root node:");

  rootComboBox.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    rootComboBoxActionPerformed(evt);
   }
  });

  createButton.setText("Create Node");
  createButton.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    createButtonActionPerformed(evt);
   }
  });

  deleteButton.setText("Delete Node");
  deleteButton.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    deleteButtonActionPerformed(evt);
   }
  });

  editButton.setText("Edit Node");
  editButton.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    editButtonActionPerformed(evt);
   }
  });

  nodeScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Nodes"));
  nodeScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

  nodeList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
  nodeList.addMouseListener(new java.awt.event.MouseAdapter() {
   public void mouseClicked(java.awt.event.MouseEvent evt) {
    nodeListMouseClicked(evt);
   }
  });
  nodeScrollPane.setViewportView(nodeList);

  saveButton.setText("Save");
  saveButton.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    saveButtonActionPerformed(evt);
   }
  });

  javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
  getContentPane().setLayout(layout);
  layout.setHorizontalGroup(
   layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addGroup(layout.createSequentialGroup()
    .addContainerGap()
    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
     .addComponent(separator4)
     .addComponent(separator3)
     .addComponent(separator2)
     .addComponent(generateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
     .addComponent(generateJava, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
     .addComponent(generateJavaScript, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
     .addComponent(separator1)
     .addComponent(directoryLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
     .addGroup(layout.createSequentialGroup()
      .addComponent(directoryTextField)
      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
      .addComponent(directoryButton))
     .addGroup(layout.createSequentialGroup()
      .addComponent(rootNodeLabel)
      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
      .addComponent(rootComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
     .addComponent(nodeScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
     .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
      .addGap(0, 0, Short.MAX_VALUE)
      .addComponent(saveButton))
     .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
      .addComponent(editButton)
      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
      .addComponent(deleteButton)
      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addComponent(createButton)
      .addGap(4, 4, 4)))
    .addContainerGap())
  );
  layout.setVerticalGroup(
   layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addGroup(layout.createSequentialGroup()
    .addContainerGap()
    .addComponent(generateLabel)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(generateJava)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
    .addComponent(generateJavaScript)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
    .addComponent(separator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(directoryLabel)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
     .addComponent(directoryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
     .addComponent(directoryButton))
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(separator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
     .addComponent(rootNodeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
     .addComponent(rootComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
    .addComponent(separator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(nodeScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
     .addComponent(createButton)
     .addComponent(deleteButton)
     .addComponent(editButton))
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(separator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(saveButton)
    .addContainerGap())
  );

  pack();
 }// </editor-fold>//GEN-END:initComponents

 private void directoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_directoryButtonActionPerformed
  JFileChooser directoryChooser = new JFileChooser();
  int returnVal = directoryChooser.showOpenDialog(this);

  if (returnVal == JFileChooser.APPROVE_OPTION) {
   setProjectRoot(directoryChooser.getSelectedFile());
  }
 }//GEN-LAST:event_directoryButtonActionPerformed

 private void nodeListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nodeListMouseClicked
  if (evt.getClickCount() == 2) {
   edit();
  }
 }//GEN-LAST:event_nodeListMouseClicked

 private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
  edit();
 }//GEN-LAST:event_editButtonActionPerformed

 private void directoryTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_directoryTextFieldActionPerformed
  setProjectRoot(new File(directoryTextField.getText()));
 }//GEN-LAST:event_directoryTextFieldActionPerformed

 private void generateJavaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateJavaActionPerformed
  generation = GENERATION_JAVA;
 }//GEN-LAST:event_generateJavaActionPerformed

 private void generateJavaScriptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateJavaScriptActionPerformed
  generation = GENERATION_JAVASCRIPT;
 }//GEN-LAST:event_generateJavaScriptActionPerformed

 private void rootComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rootComboBoxActionPerformed
  tree.setRoot(nodes.get(rootComboBox.getSelectedItem()));
 }//GEN-LAST:event_rootComboBoxActionPerformed

 private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
  nodes.remove(nodeList.getSelectedValue());
  refreshLists();
 }//GEN-LAST:event_deleteButtonActionPerformed

 private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
  DialogueNode node = new DialogueNode();
  String nodeKey = "new node";
  int i = 1;
  while (nodes.containsKey(nodeKey)) {
   nodeKey = "new node " + i;
   i++;
  }
  nodes.put(nodeKey, node);
  refreshLists();
 }//GEN-LAST:event_createButtonActionPerformed

 private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
  try {
   switch (generation) {
    case GENERATION_JAVASCRIPT:
     //TODO: Javascript Generation
     break;
    case GENERATION_JAVA:
    default:
     File saveFile = new File(
             projectRoot.getAbsolutePath() + File.separator
             + "Dialogue.java");
     saveFile.createNewFile();
     FileOutputStream out = new FileOutputStream(saveFile);
     String source
             = "import com.epicest.reusables.dialogue.*;\n"
             + "public class Dialogue {\n";
     for (String key : nodes.keySet()) {
      source += "private static DialogueNode " + key + " = new DialogueNode();\n";
     }
     source += "public static DialogueTree getDialogue(){\n"
             + "DialogueTree dt = new DialogueTree();\n";
     for (String key : nodes.keySet()) {
      DialogueNode node = nodes.get(key);
      String nodeIn = node.getIn();
      String nodeOut = node.getOut();
      String nodeAudio = node.getAudio();
      String nodeListener = node.getSPL();
      if ((nodeIn != null) && (!nodeIn.equals(""))) {
       source += key + ".setIn(\"" + nodeIn + "\");\n";
      }
      if ((nodeOut != null) && (!nodeOut.equals(""))) {
       source += key + ".setOut(\"" + nodeOut + "\");\n";
      }
      if ((nodeAudio != null) && (!nodeAudio.equals(""))) {
       source += key + ".setAudio(\"" + nodeAudio + "\");\n";
      }
      if ((nodeListener != null) && (!nodeListener.equals(""))) {
       source += key + ".setDialogueSoundPlayEventListener(new " + nodeListener + "());\n";
      }
      for (DialogueNode node2 : node.getChildren()) {
       String key2 = NodeEditor.getKey(nodes, node2);
       source += key + ".addChild(" + key2 + ");\n";
      }
     }
     source += "dt.setRoot(" + rootComboBox.getSelectedItem() + ");\n"
             + "return dt;\n"
             + "}\n"
             + "}\n";
     //System.out.print(source);
     for (char cr : source.toCharArray()) {
      out.write(cr);
     }
     out.close();
     break;
   }
  } catch (IOException ex) {
   Logger.getLogger(TreeEditor.class.getName()).log(Level.SEVERE, null, ex);
  }
 }//GEN-LAST:event_saveButtonActionPerformed

 protected void edit() {
  String node = nodeList.getSelectedValue();
  edit(node);
 }

 public void edit(String node) {
  if (node != null) {
   NodeEditor nodeEditor = new NodeEditor(this, node);
   nodeEditor.setVisible(true);
  }
 }

 protected void setProjectRoot(File newRoot) {
  String projectName = newRoot.getName();
  File oldRoot = projectRoot;
  projectRoot = newRoot;
  int files = 0;
  if (projectRoot.isDirectory()) {
   for (File file : projectRoot.listFiles()) {
    files++;
   }
  } else {
   files--;
  }
  if (files != 0) {
   int i = 1;
   while (projectRoot.exists()) {
    projectRoot = new File(projectName + " " + i);
    i++;
   }
  }
  projectRoot.mkdir();
  directoryTextField.setText(getRootPath());
  if (oldRoot != null) {
   for (File file : oldRoot.listFiles()) {
    file.renameTo(new File(projectRoot.getAbsolutePath() + File.separator + file.getName()));
   }
   oldRoot.delete();
  }
 }

 public String getRootPath() {
  return projectRoot.getAbsolutePath();
 }

 void refreshLists() {
  //nodeList, rootComboBox
  DefaultComboBoxModel<String> cbm = new DefaultComboBoxModel<String>();
  DefaultListModel<String> lm = new DefaultListModel<String>();
  for (String modelItems : nodes.keySet()) {
   cbm.addElement(modelItems);
   lm.addElement(modelItems);
  }
  rootComboBox.setModel(cbm);
  nodeList.setModel(lm);
 }

 // Variables declaration - do not modify//GEN-BEGIN:variables
 private javax.swing.JButton createButton;
 private javax.swing.JButton deleteButton;
 private javax.swing.JButton directoryButton;
 private javax.swing.JLabel directoryLabel;
 private javax.swing.JTextField directoryTextField;
 private javax.swing.JButton editButton;
 private javax.swing.ButtonGroup generateGroup;
 private javax.swing.JRadioButton generateJava;
 private javax.swing.JRadioButton generateJavaScript;
 private javax.swing.JLabel generateLabel;
 javax.swing.JList<String> nodeList;
 private javax.swing.JScrollPane nodeScrollPane;
 private javax.swing.JComboBox<String> rootComboBox;
 private javax.swing.JLabel rootNodeLabel;
 private javax.swing.JButton saveButton;
 private javax.swing.JSeparator separator1;
 private javax.swing.JSeparator separator2;
 private javax.swing.JSeparator separator3;
 private javax.swing.JSeparator separator4;
 // End of variables declaration//GEN-END:variables

}
