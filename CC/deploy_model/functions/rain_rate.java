CustomModelDownloadConditions conditions = new CustomModelDownloadConditions.Builder()
    .requireWifi()
    .build();
FirebaseModelDownloader.getInstance()
    .getModel("rain_rate", DownloadType.LOCAL_MODEL, conditions)
    .addOnSuccessListener(new OnSuccessListener<CustomModel>() {
      @Override
      public void onSuccess(CustomModel model) {
        // Download complete. Depending on your app, you could enable
        // the ML feature, or switch from the local model to the remote
        // model, etc.
      }
    });