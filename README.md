# Android_Traveler
> This app is my project taken in Mobile Development in the Hong Kong Polytechnic University.</br>
> Outline : This is a travel app which is going to record your journey where you went.
> I am also going to take some note here for my learning path.</br>
> [ListView with Adapter](https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView) is the reference what I looked before if you want to know you also can learn from it.</br>
> [Android Building Layout with adpter](https://developer.android.com/guide/topics/ui/declaring-layout.html#AdapterViews)

- Four Basic Component in Android
  - [x] Activity
  - [ ] Service
  - [ ] Broadcast
  - [x] Content Provider
  
The tick means what I use in the app, and the topic is discussed in this project.

- Building Layouts with an Adapter
  - We can use this adpter to provide view to Adapter views.
  - It can be used with list-based user interface widgets such as **ListView and Spinner**
  - If you want to customize th view what you want, you can override the method *getView*
    - getView(int, View , ViewGroup) and inflate the view resoure in the function
  - Adapter behave as a middleman between the data source and the AdapterView
  - Android provides serveral subclasses of adpater that are useful for retrieving different kinds of data and building view for an AdapterView 
    1. ArrayAdapter
    2. SimpleCursor Adapter
   
- How to build a simple listview ?
  1. Create a listview
  2. Set up the arraylist Content
  3. Set up the arrayAdapter(Context,ListViewItem,ListViewItemData,arrayData)
  4. listView set adapter

- How t build a custom listView ?
  1. Create a listview
  2. Add a new view in new layout
  3. Set up the object which is going to put into arrayList
  4. Set up the list content in arrayList
  5. Create a custom adapter (you need to inflate the data when you call *getView* method and setup the content).
  6. If you want you also can add onItemClickListener
 ```Java
     @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.place_layout,parent,false);
            //inflate the layout
        }
        ImageView imageView = (ImageView)convertView.findViewById(R.id.locationImage);
        TextView textView = (TextView)convertView.findViewById(R.id.location);
        Location location = getItem(position);
        textView.setText(location.getLocation());
        imageView.setImageResource(location.getImageID());
        return convertView;
    }
 ```
   
