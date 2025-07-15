package com.example.salescalculator; // <--- MAKE SURE THIS MATCHES YOUR PROJECT'S PACKAGE NAME// IMPORTANT: Ensure this matches your project's package name

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast; // For showing short messages

public class SalesCalculatorActivity extends AppCompatActivity {

    // Declare interface widgets
    EditText etUnitPrice1, etQuantity1;
    TextView tvItemTotal1;

    EditText etUnitPrice2, etQuantity2;
    TextView tvItemTotal2;

    EditText etUnitPrice3, etQuantity3;
    TextView tvItemTotal3;

    TextView tvGrandTotal;
    Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Links to your layout file

        // Initialize interface widgets by finding their IDs from the layout
        // Item 1
        etUnitPrice1 = findViewById(R.id.edit_text_unit_price_1);
        etQuantity1 = findViewById(R.id.edit_text_quantity_1);
        tvItemTotal1 = findViewById(R.id.text_view_item_total_1);

        // Item 2
        etUnitPrice2 = findViewById(R.id.edit_text_unit_price_2);
        etQuantity2 = findViewById(R.id.edit_text_quantity_2);
        tvItemTotal2 = findViewById(R.id.text_view_item_total_2);

        // Item 3
        etUnitPrice3 = findViewById(R.id.edit_text_unit_price_3);
        etQuantity3 = findViewById(R.id.edit_text_quantity_3);
        tvItemTotal3 = findViewById(R.id.text_view_item_total_3);

        // Grand Total and Calculate Button
        tvGrandTotal = findViewById(R.id.text_view_grand_total);
        btnCalculate = findViewById(R.id.button_calculate);

        // Set an OnClickListener for the calculate button
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTotals(); // Call the method to perform calculations
            }
        });
    }

    /**
     * This method reads values from EditTexts, calculates item totals and grand total,
     * and displays them in the respective TextViews.
     */
    private void calculateTotals() {
        double grandTotal = 0.0;

        // --- Calculate for Item 1 ---
        double item1Price = getDoubleFromEditText(etUnitPrice1);
        int item1Quantity = getIntFromEditText(etQuantity1);
        double item1Total = item1Price * item1Quantity;
        tvItemTotal1.setText(String.format("Item 1 Total: %.2f", item1Total)); // Display formatted total
        grandTotal += item1Total;

        // --- Calculate for Item 2 ---
        double item2Price = getDoubleFromEditText(etUnitPrice2);
        int item2Quantity = getIntFromEditText(etQuantity2);
        double item2Total = item2Price * item2Quantity;
        tvItemTotal2.setText(String.format("Item 2 Total: %.2f", item2Total));
        grandTotal += item2Total;

        // --- Calculate for Item 3 ---
        double item3Price = getDoubleFromEditText(etUnitPrice3);
        int item3Quantity = getIntFromEditText(etQuantity3);
        double item3Total = item3Price * item3Quantity;
        tvItemTotal3.setText(String.format("Item 3 Total: %.2f", item3Total));
        grandTotal += item3Total;

        // --- Display Grand Total ---
        tvGrandTotal.setText(String.format("Grand Total: %.2f", grandTotal));
    }

    /**
     * Helper method to safely parse a double from an EditText.
     * Shows a Toast message if input is invalid.
     */
    private double getDoubleFromEditText(EditText editText) {
        String text = editText.getText().toString().trim();
        if (text.isEmpty()) {
            // Treat empty input as 0.0 to avoid crashing, but could also prompt for input
            return 0.0;
        }
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers for price.", Toast.LENGTH_SHORT).show();
            // Optionally clear the invalid input
            // editText.setText("");
            return 0.0;
        }
    }

    /**
     * Helper method to safely parse an integer from an EditText.
     * Shows a Toast message if input is invalid.
     */
    private int getIntFromEditText(EditText editText) {
        String text = editText.getText().toString().trim();
        if (text.isEmpty()) {
            // Treat empty input as 0 to avoid crashing
            return 0;
        }
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers for quantity.", Toast.LENGTH_SHORT).show();
            // Optionally clear the invalid input
            // editText.setText("");
            return 0;
        }
    }
}