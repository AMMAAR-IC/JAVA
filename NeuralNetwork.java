import java.util.Random;

class NeuralNetwork {
    double[][] weightsIH; // input → hidden
    double[][] weightsHO; // hidden → output
    double[] hiddenBias, outputBias;
    double lr = 0.1;

    NeuralNetwork(int input, int hidden, int output) {
        Random r = new Random();
        weightsIH = new double[hidden][input];
        weightsHO = new double[output][hidden];
        hiddenBias = new double[hidden];
        outputBias = new double[output];

        for (int i = 0; i < hidden; i++)
            for (int j = 0; j < input; j++)
                weightsIH[i][j] = r.nextDouble() * 2 - 1;

        for (int i = 0; i < output; i++)
            for (int j = 0; j < hidden; j++)
                weightsHO[i][j] = r.nextDouble() * 2 - 1;
    }

    double[] feedForward(double[] inputs) {
        double[] hidden = new double[hiddenBias.length];
        for (int i = 0; i < hidden.length; i++) {
            hidden[i] = hiddenBias[i];
            for (int j = 0; j < inputs.length; j++) hidden[i] += weightsIH[i][j] * inputs[j];
            hidden[i] = sigmoid(hidden[i]);
        }

        double[] outputs = new double[outputBias.length];
        for (int i = 0; i < outputs.length; i++) {
            outputs[i] = outputBias[i];
            for (int j = 0; j < hidden.length; j++) outputs[i] += weightsHO[i][j] * hidden[j];
            outputs[i] = sigmoid(outputs[i]);
        }
        return outputs;
    }

    void train(double[] inputs, double[] targets) {
        double[] hidden = new double[hiddenBias.length];
        for (int i = 0; i < hidden.length; i++) {
            hidden[i] = hiddenBias[i];
            for (int j = 0; j < inputs.length; j++) hidden[i] += weightsIH[i][j] * inputs[j];
            hidden[i] = sigmoid(hidden[i]);
        }

        double[] outputs = new double[outputBias.length];
        for (int i = 0; i < outputs.length; i++) {
            outputs[i] = outputBias[i];
            for (int j = 0; j < hidden.length; j++) outputs[i] += weightsHO[i][j] * hidden[j];
            outputs[i] = sigmoid(outputs[i]);
        }

        double[] outputErrors = new double[targets.length];
        for (int i = 0; i < targets.length; i++) outputErrors[i] = targets[i] - outputs[i];

        for (int i = 0; i < outputs.length; i++) {
            double gradient = outputs[i] * (1 - outputs[i]) * outputErrors[i] * lr;
            for (int j = 0; j < hidden.length; j++) {
                weightsHO[i][j] += gradient * hidden[j];
            }
            outputBias[i] += gradient;
        }
    }

    static double sigmoid(double x) { return 1.0 / (1 + Math.exp(-x)); }
}

public class SimpleNN {
    public static void main(String[] args) {
        NeuralNetwork nn = new NeuralNetwork(2, 4, 1);
        double[][] inputs = { {0,0}, {0,1}, {1,0}, {1,1} };
        double[][] targets = { {0}, {1}, {1}, {0} };

        for (int epoch = 0; epoch < 5000; epoch++) {
            for (int i = 0; i < inputs.length; i++) nn.train(inputs[i], targets[i]);
        }

        for (int i = 0; i < inputs.length; i++) {
            double[] out = nn.feedForward(inputs[i]);
            System.out.printf("%d XOR %d = %.3f%n", (int)inputs[i][0], (int)inputs[i][1], out[0]);
        }
    }
}
