/**
 * Import function triggers from their respective submodules:
 *
 * const {onCall} = require("firebase-functions/v2/https");
 * const {onDocumentWritten} = require("firebase-functions/v2/firestore");
 *
 * See a full list of supported triggers at https://firebase.google.com/docs/functions
 */

const {onRequest} = require("firebase-functions/v2/https");
const logger = require("firebase-functions/logger");
const admin = require('firebase-admin');
const serviceAccount = require('../permissions.json');
admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  storageBucket: 'c23-ps377',
});
const ml = admin.machineLearning();

// First, import and initialize the SDK as shown above.

(async () => {
  // Upload the tflite file to Cloud Storage
  const storageBucket = admin.storage().bucket('c23-ps377');
  const files = await storageBucket.upload('../model/temp_min/temp_min.tflite');

  // Create the model object and add the model to your Firebase project.
  const bucket = files[0].metadata.bucket;
  const name = files[0].metadata.name;
  const gcsUri = `gs://c23-ps377.appspot.com`;
  const model = await ml.createModel({
    displayName: 'temp_min',  // This is the name you use from your app to load the model.
    tags: ['temp_min'],  // Optional tags for easier management.
    tfliteModel: { gcsTfliteUri: gcsUri },
  });

  // Publish the model.
  await ml.publishModel(model.modelId);

  process.exit();
})().catch(console.error);

// Create and deploy your first functions
// https://firebase.google.com/docs/functions/get-started

// exports.helloWorld = onRequest((request, response) => {
//   logger.info("Hello logs!", {structuredData: true});
//   response.send("Hello from Firebase!");
// });
