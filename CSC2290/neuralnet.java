import java.util.*;

public class Neuralnet {
    public double[][][][] network;

    public Neuralnet(int[] layers) {
        // constructs a new neural network
        network = new double[layers.length][][][];
        for (int i = 0; i < layers.length; i++) {
            // create layer
            int connections = 0;
            if (i < layers.length-1) {
                connections = layers[i+1];
            }
            network[i] = new double[layers[i]][connections][2]; // weight/bias
            // fill connection defaults
            for (int n = 0; n < layers[i]; n++) {
                // node
                for (int z = 0; z < connections; z++) {
                    network[i][n][z][0] = 0.5; // set default weight to 0.5
                }
            }
        }
    }

    public double sigmoid(double x) {
        // sigmoid activation function
        return 1/(1+Math.pow(Math.E,-x));
    }

    public double[] process(double[] inputData) {
        // runs the input data through the neural network
        double layerSum[] = inputData;
        for (int l = 0; l < network.length-1; l++) {
            // layer
            int nextNodes = network[l+1].length;
            double nextLayerSum[] = new double[nextNodes];
            for (int n = 0; n < network[l].length; n++) {
                // node
                for (int c = 0; c < network[l][n].length; c++) {
                    // connection
                    double weight = network[l][n][c][0];
                    double bias = network[l][n][c][1];
                    double result = (layerSum[n]*weight)+bias;
                    nextLayerSum[c] += result;
                }
            }
            layerSum = nextLayerSum;
            for (int i = 0; i < layerSum.length; i++) {
                //System.out.print(layerSum[i] + " > ");
                layerSum[i] = sigmoid(layerSum[i]);
                //System.out.println(layerSum[i]);
            }
        }
        return layerSum;
    }

    public double fitness(double[][] inputData, double[][] outputData) {
        double confusion = 0;
        int quantity = 0;
        for (int i = 0; i < inputData.length; i++) {
            double in[] = inputData[i];
            double target[] = outputData[i];
            double result[] = process(in);
            for (int n = 0; n < result.length; n++) {
                double t = target[n];
                double r = result[n];
                double deviation = Math.abs(t-r);
                //System.out.println("DEVIATION: "+deviation);
                confusion += deviation;
            }
            quantity ++;
        }
        return -(confusion/quantity);
    }

    public void train(double[][] inputData, double[][] outputData, double requiredFitness, int maxParameterIterations) {
        // trains the network on the given data sets
        double fitness = fitness(inputData, outputData);
        boolean target = false;
        int targetLayer = 0;
        int targetNode = 0;
        int targetConnection = 0;
        int targetMode = 0;
        int targetDirection = 1;
        int parameterIterations = 0;
        while (true) {
            // gradient descent
            if (!target) {
                target = true;
                // direction
                if (targetDirection >= 1) {
                    targetDirection = -1;
                }
                else if (targetDirection == -1) {
                    targetDirection = 1; 
                    targetMode++;
                }
                
                // mode
                if (targetMode > 1) {
                    targetMode = 0;
                    targetConnection++;
                }

                // connection
                if (targetConnection >= network[targetLayer][targetNode].length) {
                    targetConnection = 0;
                    targetNode++;
                }

                // node
                if (targetNode >= network[targetLayer].length) {
                    targetNode = 0;
                    targetLayer++;
                }

                // layer
                if (targetLayer >= network.length-1) {
                    targetLayer = 0;
                }

                //targetLayer = (int)((network.length-1)*Math.random());
                //targetNode = (int)(network[targetLayer].length*Math.random());
                //targetConnection = (int)(network[targetLayer][targetNode].length*Math.random());
                //targetMode = 0;//(int)(2*Math.random());
                //targetDirection = (int)(2*Math.random());
                //if (targetDirection == 0) {
                    //targetDirection = -1;
                //}
            }
            network[targetLayer][targetNode][targetConnection][targetMode] += ((double)targetDirection)/100;

            // test fitness
            double lastFitness = fitness;
            fitness = fitness(inputData, outputData);
            double delta = fitness-lastFitness;

            // print fitness
            System.out.println("FITNESS: "+fitness+"\t\t"+targetLayer+","+targetNode+","+targetConnection+" ["+targetMode+","+targetDirection+"]");
            parameterIterations++;
            if (delta <= 0 || parameterIterations > maxParameterIterations) {
                // find new target if we stop making progress
                parameterIterations = 0;
                target = false;
                //network[targetLayer][targetNode][targetConnection][targetMode] -= ((double)targetDirection)/100;
            }

            if (fitness > requiredFitness) {
                break;
            }
        }
    }

    public void displayNetwork() {
        // displays network topology
        for (int a = 0; a < network.length; a++) {
            System.out.println(a);
            for (int b = 0; b < network[a].length; b++) {
                System.out.println("  "+b);
                for (int c = 0; c < network[a][b].length; c++) {
                    System.out.println("    "+c);
                    for (int d = 0; d < network[a][b][c].length; d++) {
                        System.out.println("      "+network[a][b][c][d]);
                    }
                }
            }
        }
    }
}
