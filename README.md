# NewsApp - Android Compose Multi-Module Project

NewsApp is an Android project built with Jetpack Compose, featuring a multi-module architecture. This application provides a platform for accessing and consuming news articles, showcasing various modules handling distinct aspects of the app.

## Modules

The project is structured into the following modules:

1. **data**: Contains data-related classes and interfaces, such as repositories and data sources.
2. **domain**: Holds domain-specific classes and business logic, including use cases and domain models.
3. **model**: Defines common models used throughout the application.
4. **network**: Manages network-related operations, including API clients and network data sources.
5. **feature/dashboard**: The primary feature module displaying the news dashboard.

## Features

- Access News: Retrieve and display news articles from various sources.
- Jetpack Compose UI: Utilize the Jetpack Compose toolkit to create a modern and reactive user interface.
- Multi-module architecture: Organize code into different modules for improved organization and maintainability.
- Network calls: Fetch real-time news data from external APIs.

## Getting Started

To begin working with the NewsApp project, follow these steps:

1. Clone the repository:

   ```bash
   git clone https://github.com/ShushantTiwari-ashu/NewsApp
   ```

2. Import the project into Android Studio.

3. Build and run the application.

## How to Use

The main feature module, `feature/dashboard`, serves as the entry point for the NewsApp. It enables users to explore and read news articles from different sources.

The news access functionality is implemented using the data, domain, and network modules to fetch and display articles dynamically.

## Architecture

The NewsApp project follows a clean and modular architecture to ensure a separation of concerns and maintainability. The modules are organized as follows:

- `data`: Manages data-related operations and provides data to other modules.
- `domain`: Holds the business logic and use cases for handling news articles.
- `model`: Defines common models shared across the application.
- `network`: Manages network operations, including API calls to fetch news data.
- `feature/dashboard`: The primary feature module that showcases the news dashboard using Jetpack Compose.

## Dependencies

The NewsApp project uses the following dependencies:

- Jetpack Compose: For building the modern UI.
- HttpUrlConnection: For making network API calls.
- Coroutines: For asynchronous programming and concurrency handling.

## Contributing

Contributions to the NewsApp project are welcome! If you find a bug, have an enhancement in mind, or want to add new features, please feel free to open an issue or submit a pull request.

## License

The NewsApp project is licensed under the [MIT License](LICENSE). Feel free to use, modify, and distribute the code for personal and commercial projects.

![NewsApp Screenshot](https://github.com/ShushantTiwari-ashu/NewsApp/assets/20141043/2fb845e8-646a-41cc-81c1-79afa1ab6b73)