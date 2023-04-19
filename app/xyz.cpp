#include <iostream> 
#include <math.h> 
#include <vector> 
#include <string> 
using namespace std; 
// Defining the data structure for the AI model 
struct AI_Model 
{ 
 vector<double> weights; 
 vector<double> biases; 
}; 
// Function to initialize weights and biases 
void initWeightsBiases(AI_Model &m, vector<double> 
&weights, vector<double> &biases) 
{ 
 m.weights = weights; 
 m.biases = biases; 
} 
// Function to compute the output of the AI model 
double computeOutput(AI_Model &m, vector<double> 
&inputs) 
{ 
 double output = 0; 
 for (int i = 0; i < m.weights.size(); i++) 
 { 
 output += m.weights[i] * inputs[i]; 
 } 
 output += m.biases[0]; 
 return output; 
} 
// Function to train the AI model 
void trainAI(AI_Model &m, vector<vector<double>> &inputs, 
vector<double> &outputs, int epochs, double learning_rate) 
{ 
 int num_inputs = inputs.size(); 
 int num_weights = m.weights.size(); 
 
 for (int e = 0; e < epochs; e++) 
 { 
 // Updating the weights and biases using gradient descent 
 for (int i = 0; i < num_inputs; i++) 
 { 
 double output = computeOutput(m, inputs[i]); 
 double error = outputs[i] - output; 
 
 for (int w = 0; w < num_weights; w++) 
 { 
 m.weights[w] += learning_rate * error * inputs[i][w]; 
 } 
 m.biases[0] += learning_rate * error; 
 } 
 } 
} 
// Driver code 
int main() 
{ 
 // Initializing the weights and biases 
 vector<double> weights = { 0.1, 0.2, 0.3, 0.4 }; 
 vector<double> biases = { 0.5 }; 
 
 // Creating the AI model 
 AI_Model model; 
 
 // Initializing the weights and biases of the AI model 
 initWeightsBiases(model, weights, biases); 
 
 // Training data 
 vector<vector<double>> inputs = { {1, 0, 3, 2}, {2, 3, 1, 4}, 
 {3, 0, 2, 1}, {4, 2, 1, 0} }; 
 vector<double> outputs = { 1, 3, 2, 0 }; 
 
 // Training the AI model 
 trainAI(model, inputs, outputs, 10000, 0.1); 
 
 // Computing the output of the AI model 
 vector<double> test_input = {1, 2, 3, 4}; 
 cout << computeOutput(model, test_input) << endl; 
 
 return 0; 
}

