/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SIGUEA.GUI;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Computer22
 */
// Clase para renderizar y editar los RadioButtons dentro de la JTable
class AsistenciaRendererEditor extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {
    private final JPanel panel;
    private final JRadioButton rPresente;
    private final JRadioButton rAusente;
    private final JRadioButton rTardanza;
    private final ButtonGroup grupo;

    public AsistenciaRendererEditor() {
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 2));
        panel.setOpaque(true);

        rPresente = new JRadioButton("P");
        rAusente = new JRadioButton("A");
        rTardanza = new JRadioButton("T");

        // Quitar fondos grises de los botones para estética limpia
        rPresente.setOpaque(false);
        rAusente.setOpaque(false);
        rTardanza.setOpaque(false);

        grupo = new ButtonGroup();
        grupo.add(rPresente);
        grupo.add(rAusente);
        grupo.add(rTardanza);

        panel.add(rPresente);
        panel.add(rAusente);
        panel.add(rTardanza);

        // Al hacer clic en cualquier opción, detiene la edición para guardar el cambio
        ActionListener al = e -> stopCellEditing();
        rPresente.addActionListener(al);
        rAusente.addActionListener(al);
        rTardanza.addActionListener(al);
    }

    private void actualizarSeleccion(Object value) {
        grupo.clearSelection();
        if (value != null) {
            String val = value.toString();
            if (val.equalsIgnoreCase("P")) rPresente.setSelected(true);
            else if (val.equalsIgnoreCase("A")) rAusente.setSelected(true);
            else if (val.equalsIgnoreCase("T")) rTardanza.setSelected(true);
        }
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        actualizarSeleccion(value);
        // Mantiene el color de fondo de selección coordinado con la JTable
        if (isSelected) {
            panel.setBackground(table.getSelectionBackground());
        } else {
            panel.setBackground(table.getBackground());
        }
        return panel;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        actualizarSeleccion(value);
        panel.setBackground(table.getSelectionBackground());
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        if (rPresente.isSelected()) return "P";
        if (rAusente.isSelected()) return "A";
        if (rTardanza.isSelected()) return "T";
        return "";
    }
}
