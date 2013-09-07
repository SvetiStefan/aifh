package com.heatonresearch.aifh.general.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is used to store both the input and ideal vectors for a single item of training data.  A label can also
 * be applied.
 */
public class BasicData {
    /**
     * The input vector.
     */
    private final double[] input;

    /**
     * The ideal (or expected output) vector.
     */
    private final double[] ideal;

    /**
     * A label, that can be used to tag this element.
     */
    private String label;

    /**
     * Construct an empty unsupervised element.  An unsupervised element does not have an expected output.
     *
     * @param theInputDimensions The number of dimensions.
     */
    public BasicData(int theInputDimensions) {
        this(theInputDimensions, 0, null);
    }

    /**
     * Construct an empty supervised element.  A supervised element has both input an ideal.
     *
     * @param theInputDimensions The dimensions for the input vector.
     * @param theIdealDimensions The dimensions for the ideal vector.
     */
    public BasicData(int theInputDimensions, int theIdealDimensions) {
        this(theInputDimensions, theIdealDimensions, null);
    }

    public BasicData(int theInputDimensions, int theIdealDimensions, String theLabel) {
        this.label = theLabel;
        this.input = new double[theInputDimensions];
        this.ideal = new double[theIdealDimensions];
    }

    /**
     * Construct a supervised element, with a label.
     *
     * @param theInputData The input data vector.
     * @param theIdealData The ideal data vector.
     * @param theLabel     The label.
     */
    public BasicData(double[] theInputData, double[] theIdealData, String theLabel) {
        this.label = theLabel;
        this.input = theInputData;
        this.ideal = theIdealData;
    }

    /**
     * Construct an unsupervised element, with a label.
     *
     * @param theInputData The input vector.
     * @param theLabel     The label.
     */
    public BasicData(double[] theInputData, String theLabel) {
        this(theInputData, new double[0], theLabel);
    }

    /**
     * Construct an unsupervised element, without a label.
     *
     * @param theInputData The input vector.
     */
    public BasicData(double[] theInputData) {
        this(theInputData, null);
    }

    /**
     * @return The input vector.
     */
    public double[] getInput() {
        return input;
    }

    /**
     * @return The ideal vector.
     */
    public double[] getIdeal() {
        return ideal;
    }

    /**
     * @return The label vector.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Set the label.
     *
     * @param label The label.
     */
    public void setLabel(final String label) {
        this.label = label;
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[BasicData: input:");
        result.append(Arrays.toString(this.input));
        result.append(", ideal:");
        result.append(Arrays.toString(this.ideal));
        result.append(", label:");
        result.append(this.label);
        result.append("]");

        return result.toString();
    }

    /**
     * Convert two 2D arrays into a List of BasicData elements.  One array holds input and the other ideal vectors.
     *
     * @param inputData An array of input vectors.
     * @param idealData An array of ideal vectors.
     * @return A list of BasicData elements.
     */
    public static List<BasicData> convertArrays(final double[][] inputData, final double[][] idealData) {
        // create the list
        List<BasicData> result = new ArrayList<BasicData>();

        // get the lengths
        int inputCount = inputData[0].length;
        int idealCount = idealData[0].length;

        // build the list
        for (int row = 0; row < inputData.length; row++) {
            BasicData dataRow = new BasicData(inputCount, idealCount);
            System.arraycopy(inputData[row], 0, dataRow.getInput(), 0, inputCount);
            System.arraycopy(idealData[row], 0, dataRow.getIdeal(), 0, idealCount);
            result.add(dataRow);
        }

        return result;
    }

}