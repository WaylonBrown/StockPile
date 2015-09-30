# StockPile
*An extremely small library to allow a flexible SharedPreferences with less boilerplate and the ability to store and retrieve objects using Gson.*

The original goal of this library was to remove the boilerplate of SharedPreferences (3 lines of code every time you want to do one storage/retrieval). Laziness then resulted in adding the following things:

* Flexible storing 
  * Using the same method to store any value or object, with an optional flag for storing asynchronously using apply() or the standard commit()
* Allowing default values to be optional in retrieving
* Baked in Gson to allow you to store and retrieve objects


To use StockPile:

```
@Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      //needs to be called once before using the library
      StockPile.initialize(this);

      //storing various types of values
      StockPile.store("key1", "value");
      StockPile.store("key2", true);
      StockPile.store("key3", 10.5f, true); //optionally add the third parameter to flag whether to store asynchronously (faster) or not
      boolean success = StockPile.store("key4", 5);   //if not storing asynchronously, it returns whether the storage was successful or not, just like SharedPrefs
      StockPile.store("key5", 2453L);

      String stringVal = StockPile.getString("key1", "");
      boolean boolVal = StockPile.getBoolean("key2"); //2nd parameter (default value) can be left out
      float floatVal = StockPile.getFloat("key3", 0F);
      int intVal = StockPile.getInt("key4", 0);
      long longVal = StockPile.getLong("key5", 0L);

      List<String> purchases = new ArrayList<>();
      purchases.add("purchase1");
      purchases.add("purchase2");
      purchases.add("purchase3");
      purchases.add("purchase4");
      purchases.add("purchase5");
      CustomerObject customer = new CustomerObject("Example Name", 10, 25, purchases);

      StockPile.store("key6", customer);
      CustomerObject storedCustomer = (CustomerObject)StockPile.getObject("key6", CustomerObject.class);
  }
```
