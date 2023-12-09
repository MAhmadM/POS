import BusinessLayer.*;
import DAO.*;
//import DAO.OrderDAO;
import GUI.LoginGUI;
import org.bson.types.ObjectId;

public class Main {
    public static void main(String[] args) {

//        Category subcategory1 = new Category();
////        subcategory1.setCode("1");
//        subcategory1.setName("Subcategory 1");
//        subcategory1.setDescription("Description for Subcategory 1");
//
//        Category subcategory2 = new Category();
////        subcategory2.setCode("2");
//        subcategory2.setName("Subcategory 2");
//        subcategory2.setDescription("Description for Subcategory 2");
//
//
//        Category subcategory3 = new Category();
////        subcategory3.setCode("3");
//        subcategory3.setName("Subcategory 3");
//        subcategory3.setDescription("Description for Subcategory 3");
//
//
//        Category subcategory4 = new Category();
////        subcategory4.setCode("4");
//        subcategory4.setName("Subcategory 4");
//        subcategory4.setDescription("Description for Subcategory 4");
//
//
//
//        Category mainCategory = new Category();
////        mainCategory.setCode("0");
//        mainCategory.setName("Main Category");
//        mainCategory.setDescription("Description for Main Category");
//
//        Product product1 = new Product();
//        product1.setCode("1");
//        product1.setName("Product 1");
//        product1.setPrice(10.0);
//        product1.setStockQuantity(100);
//
//
//        Product product2 = new Product();
//        product2.setCode("2");
//        product2.setName("Poko");
//        product2.setPrice(15.0);
//        product2.setStockQuantity(50);
//
//
//
//        subcategory4.add(product1);
//        subcategory2.add(product2);
//        subcategory3.add(subcategory4);
//        subcategory1.add(subcategory3);
//        mainCategory.add(subcategory1);
//        mainCategory.add(subcategory2);
//        CategoryDAO categoryDAO= new CategoryDAO();
//        categoryDAO.createCategory(mainCategory);
//
//        Category foundCategory = categoryDAO.findCategoryByName("Main Category");
//        if (foundCategory != null) {
//            System.out.println("Found category: " + foundCategory.getName());
//            System.out.println("Subcategories: " + foundCategory.getSubcategories().size());
//            System.out.println("Products: " + foundCategory.getProducts().size());
//        } else {
//            System.out.println("Category not found.");
//        }
//
//        System.out.println("Found category: " + foundCategory.getName());
//        //subcategory1.remove(subcategory3);
//        categoryDAO.getCategoriesWithSubcategoriesAndProductsByName(mainCategory.getName());
//        ProductDAO productDAO = new ProductDAO();
//
//
//        Item item1 =new Item();
////        item1.setId("1");
//        item1.setProduct(product1);
//        item1.setQuantityOrdered(7);
//        item1.setPrice();
//
//        Item item2 =new Item();
////        item2.setId("2");
//        item2.setProduct(product2);
//        item2.setQuantityOrdered(5);
//        item2.setPrice();
//
//        ItemContainer itemContainer=new Cart();
//        itemContainer.add(item1);
//        itemContainer.add(item2);
////        itemContainer.setId("1");
//        CartDAO cartDAO=new CartDAO();
//        cartDAO.createCart((Cart) itemContainer);
//        cartDAO.getCart((Cart) itemContainer);
//        System.out.println(cartDAO.findCartById(itemContainer.getId()).total());
//
//        ItemContainer itemContainer1 = new Order();
//        itemContainer1.add(item1);
//        itemContainer1.add(item2);
////        itemContainer1.setId("2");
//
//        Order order = (Order) itemContainer1;
//        order.setCustomer("Muhammad Ahmad");
//        order.setTime();
//
//        OrderDAO orderDAO = new OrderDAO();
//        orderDAO.createOrder(order);
//        orderDAO.getOrder(order);
////        User user = new Manager();
////        user.setName("Muhammad Ahmad");
////        user.setPassword("1234");
////        UserDAO userDAO = new UserDAO();
////        userDAO.createUser(user);
////        System.out.println(userDAO.findUserByUsernameAndPassword("Muhammad Ahmad","1234").getName());
//        System.out.println("achaaa"+product2.getCategoryofProduct());

//        User user = new SalesAssistant();
//        user.setName("hassan");
//        user.setPassword("1234");
//        UserDAO userDAO = new UserDAO();
//        userDAO.createUser(user);
//
        LoginGUI login = new LoginGUI();
        login.setVisible(true);

    }
}
