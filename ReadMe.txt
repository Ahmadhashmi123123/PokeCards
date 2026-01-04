PokeCards – Pokémon Cards Viewer App

PokeCards is an Android application that displays Pokémon cards in a modern grid layout with a clean UI.  
The app allows users to log in, browse Pokémon cards, search cards by name, view enlarged card images, and manage favourites.

1. Features

🔐 Authentication
- Login system using SharedPreferences
- Signup screen
- Forget Password functionality
- Session handling (Login / Logout)

2. 🏠 Home Screen (Cards Screen)
- Pokémon cards displayed in a Grid RecyclerView
- Cards loaded from local JSON file (assets)
- Smooth scrolling and optimized performance
- Toolbar with centered logo
- Search Pokémon by name using SearchView
- Rounded card design using CardView + Drawable


3. 🔍 Search Functionality
- Real-time Pokémon search from toolbar
- Case-insensitive filtering
- Works smoothly with RecyclerView adapter

4. 🖼️ Card Image Preview
- Tap on any card to open a full-screen enlarged image
- Dark transparent background for focus
- Tap image to close preview
- Implemented using Dialog + Glide

5. ⭐ Favourites System
- Add Pokémon cards to favourites
- Favourites stored using a Singleton Favourite Manager
- Separate favourites screen
- No duplicate favourites allowed

6. 👤 Profile & Menu Options
- Action Menu in Toolbar:
  - About App
  - Favourites
  - Sign Out
- User session cleared on logout
- Smooth navigation between screens

7. 🛠️ Technologies & Components Used

- Java
- Android SDK
- RecyclerView
- GridLayoutManager
- CardView
- Toolbar
- SearchView
- Dialog
- SharedPreferences
- Singleton Pattern
- Gson (JSON Parsing)
- Glide  (Image Loading)
- ConstraintLayout
- Drawable Resources (Rounded Cards)
- Intents & Activities

8. 📂 Data Source
- Pokémon card data loaded from a local JSON file
- Card details include:
  - Name
  - Image URL
  - Type
  - HP
  - Rarity
  - Pack
  - Artist
  - EX status
  - Full Art status

9. 🎨 UI/UX Highlights
- Clean and modern interface
- Rounded card views
- Responsive layouts
- Consistent theme colors
- Toolbar logo aligned at center
- Smooth user experience

10. 📌 Future Improvements
- Online API integration
- User profile image
- Dark mode support
- Cloud-based favourites
- Animations and transitions

👨‍💻 Developer

Developed by Ahmad Hashmi  
PokeCards v1.0
23-ARID-686