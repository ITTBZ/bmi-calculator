package io.genetv.bmicalculator.bmi;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Arrays;

public enum BMIClassification {

    UNDERWEIGHT("underweight", 0, 18.4),
    HEALTHY("healthy", 18.5, 24.9),
    PRE_OBESITY("obesity-pre", 25.0, 29.9),
    OBESITY_I("obesity-1", 30.0, 34.9),
    OBESITY_II("obesity-2", 35.0, 39.9),
    OBESITY_III("obesity-3", 40.0, -1);

    private String keyName;
    private double minWeight;
    private double maxWeight;

    BMIClassification(String keyName, double minWeight, double maxWeight) {
        this.keyName = keyName;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
    }

    /**
     * Get the key, of a specific enum, that can be used to get the classification name out of our translations.
     * @return the translation key
     */
    public String getKeyName() {
        return "weight." + this.keyName + "_name";
    }

    /**
     * We stream all enum values and find the one matching our weight by comparing it to the min and max weight of the given classifications.
     * @param weight the weight to find the classification of
     * @return the BMIClassification matching the min and max weight of the classification
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static BMIClassification fromWeight(double weight) {
        return Arrays.stream(values()).filter( c -> weight >= c.minWeight && (weight <= c.maxWeight || c.maxWeight == -1)).findFirst().get();
    }
}
