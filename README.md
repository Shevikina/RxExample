Минимальный проект для примера использования RxJava
# Задание

1. Есть
  - список товаров 
  - список категорий с подкатегориями

2. Необходимо 
  - запросить список подкатегорий по id категории 
  - вывести все товары соответсвуюющие этим подкатегориям 
    - запрос на товары может принимать список id
    - тут можно на основе flatMap

# Описание
Domain:
- [Category](https://github.com/Shevikina/RxExample/blob/main/app/src/main/java/com/example/rxexample/domain/models/Category.kt) - модель данных для категорий
- [Subcategory](https://github.com/Shevikina/RxExample/blob/main/app/src/main/java/com/example/rxexample/domain/models/Subcategory.kt) - модель данных для подкатегорий
- [Product](https://github.com/Shevikina/RxExample/blob/main/app/src/main/java/com/example/rxexample/domain/models/Product.kt) - модель данных для продуктов
- [CategoryRepository](https://github.com/Shevikina/RxExample/blob/main/app/src/main/java/com/example/rxexample/domain/repositories/CategoryRepository.kt) и [ProductRepository](https://github.com/Shevikina/RxExample/blob/main/app/src/main/java/com/example/rxexample/domain/repositories/ProductRepository.kt) - интерфейсы для репозиториев с категориями и с продуктами

Data:
- [MocSource](https://github.com/Shevikina/RxExample/blob/main/app/src/main/java/com/example/rxexample/data/data_source/MocSource.kt) - Моковые данные 
- [CategoryRepositoryImpl](https://github.com/Shevikina/RxExample/blob/main/app/src/main/java/com/example/rxexample/data/repositories/CategoryRepositoryImpl.kt) и [ProductRepositoryImpl](https://github.com/Shevikina/RxExample/blob/main/app/src/main/java/com/example/rxexample/data/repositories/ProductRepositoryImpl.kt) - реализации репозиториев для категорий и продуктов
- [ProductService](https://github.com/Shevikina/RxExample/blob/main/app/src/main/java/com/example/rxexample/data/ProductService.kt) - сервис, содержащий всю логику, реализованную через вызов методов из репозиториев

UI
- [MainActivity](https://github.com/Shevikina/RxExample/blob/main/app/src/main/java/com/example/rxexample/MainActivity.kt) - содержит инстанс сервиса и определяет общую логику взаимодействия с ним
- ProductScreen - метод, для отображения информации полученной из сервиса

# Результаты

Первое открытие

<img width="320" alt="Снимок экрана 2025-07-03 в 20 38 12" src="https://github.com/user-attachments/assets/7b0c8523-772c-4307-afd7-152fe0f25764" />

---
Выбрана "Еда"

<img width="272" alt="Снимок экрана 2025-07-03 в 20 39 50" src="https://github.com/user-attachments/assets/264cd394-88a2-4d6a-bc07-000f1b106db5" />

---
Выбраны "Хозтовары"

<img width="216" alt="Снимок экрана 2025-07-03 в 20 55 34" src="https://github.com/user-attachments/assets/68896b5d-af1d-43a3-a706-3530de5ba1d9" />
