package SWProject.Model;

import java.io.*;
import java.util.ArrayList;

import static SWProject.Control.SharedController.database;

public class Database
{
    private ArrayList<User>users;
    private ArrayList<Product>products;
    private ArrayList<ShoppingCart>shoppingCarts;
    private ArrayList<Store>stores;
    private ArrayList<Brand>brands;
    private ArrayList<Transaction>transactions;
    private ArrayList<VoucherCard>voucherCards;
    private ArrayList<Suggest>suggests;

    public Database()
    {
        users=new ArrayList<>();
        products=new ArrayList<>();
        shoppingCarts=new ArrayList<>();
        stores=new ArrayList<>();
        brands=new ArrayList<>();
        voucherCards=new ArrayList<>();
        transactions=new ArrayList<>();
        suggests=new ArrayList<>();
    }

    public User loadUser(String ID) {
        for(int i=0;i<users.size();i++)
        {
            if(users.get(i).userID.equals(ID))
                return users.get(i);
        }
        return null;
    }

    public void addUser(User user)
    {
        users.add(user);
        try {
            write("Users");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) throws IOException {
        for(int i=0;i<users.size();i++)
        {
            if(users.get(i).userID.equals(user.userID))
            {
                users.add(i,user);
                write("Users");
                break;
            }
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public  Product loadProduct(int ID) {
        for(int i=0;i<products.size();i++)
        {
            if(products.get(i).productID==ID)
                return products.get(i);
        }
        return null;
    }

    public void addProduct(Product product) {
        products.add(product);
        try {
            write("Products");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeProduct(int ID) {
        for(int i=0;i<products.size();i++)
        {
            if(products.get(i).productID==ID)
            {
                System.out.println(1);
                products.remove(i);
                try {
                    write("Products");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public void updateProduct(Product product)
    {
        for(int i=0;i<products.size();i++)
        {
            if(products.get(i).productID==product.productID)
            {
                products.set(i,product);
                try {
                    write("Products");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }

    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public ShoppingCart loadShoppingCart(String userID)
    {
        for(int i=0;i<shoppingCarts.size();i++)
        {
            if(shoppingCarts.get(i).userID.equals(userID))
            {
                return shoppingCarts.get(i);
            }
        }
        return null;
    }

    public void  updateShoppingCarts(ShoppingCart shoppingCart) throws IOException {
        for(int i=0;i<shoppingCarts.size();i++)
        {
            if(shoppingCarts.get(i).userID.equals(shoppingCart.userID))
            {
                System.out.println(1);
                System.out.println();
                shoppingCarts.set(i,shoppingCart);
                write("Carts");
                break;
            }
        }
    }


    public void addShoppingCart(ShoppingCart shoppingCart) throws IOException {
        shoppingCarts.add(shoppingCart);
        write("Carts");
    }

    public ArrayList<VoucherCard> getVoucherCards() {
        return voucherCards;
    }

    public VoucherCard loadVoucherCard(int ID)
    {
        for(int i=0;i<voucherCards.size();i++)
            if(voucherCards.get(i).getID()==ID)
                return voucherCards.get(i);
        return null;
    }

    public void removeVoucherCard(int ID)
    {
        for(int i=0;i<voucherCards.size();i++)
        {
            if(voucherCards.get(i).getID()==ID)
            {
                voucherCards.remove(i);
                try {
                    write("Vouchers");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
    public void addVoucherCard(VoucherCard voucherCard) {
        if(voucherCards.size()==0)
            voucherCard.setID(0);
        else
            voucherCard.setID(voucherCards.get(voucherCards.size()-1).getID()+1);
        voucherCards.add(voucherCard);
        try {
            write("Vouchers");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Store loadStore(int ID) {
        for(int i=0;i<stores.size();i++)
        {
            if(stores.get(i).storeID ==ID)
                return stores.get(i);
        }
        return null;
    }

    public void addStore(Store store) {
        stores.add(store);
        try {
            database.write("Stores");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateStore(Store store)
    {
        for(int i=0;i<stores.size();i++)
        {
            if(stores.get(i).storeID==store.storeID)
            {
                stores.set(i,store);
                try {
                    write("Stores");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public void removeStore(int ID) {
        for(int i=0;i<stores.size();i++)
        {
            if(stores.get(i).storeID ==ID)
            {
                stores.remove(i);
                try {
                    write("Stores");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }

    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransactions(Transaction transaction) throws IOException
    {
        if(transactions.size()==0)
            transaction.setID(0);
        else
            transaction.setID(transactions.get(transactions.size()-1).getID()+1);
        transactions.add(transaction);
        write("Transactions");

    }

    public ArrayList<Suggest> getSuggests() {
        return suggests;
    }

    public void addSuggest(Suggest suggest)
    {
        if(suggests.size()==0)
            suggest.setSuggestID(0);
        else
            suggest.setSuggestID(suggests.get(suggests.size()-1).getSuggestID()+1);
        suggests.add(suggest);
        try {
            write("Suggests");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String path) throws IOException {
        File file=new File(path+".txt");
        FileOutputStream fileOutputStream=new FileOutputStream(file);
        fileOutputStream.write(("").getBytes());
        fileOutputStream.close();
        fileOutputStream=new FileOutputStream(file);
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
        if(path.equals("Products"))
            objectOutputStream.writeObject(database.products);
        if(path.equals("Stores"))
            objectOutputStream.writeObject(database.stores);
        if(path.equals("Users"))
            objectOutputStream.writeObject(database.users);
        if(path.equals("Carts"))
            objectOutputStream.writeObject(database.shoppingCarts);
        if(path.equals("Transactions"))
            objectOutputStream.writeObject(database.transactions);
        if(path.equals("Vouchers"))
            objectOutputStream.writeObject(database.voucherCards);
        if(path.equals("Suggests"))
            objectOutputStream.writeObject(database.suggests);
        objectOutputStream.flush();
        objectOutputStream.close();
        fileOutputStream.close();
    }

    public void read(String path) throws IOException, ClassNotFoundException {
        File file=new File(path+".txt");
        if(file.length()!=0) {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            if (path.equals("Products"))
                database.products = (ArrayList<Product>) objectInputStream.readObject();
            if (path.equals("Users"))
                database.users = (ArrayList<User>) objectInputStream.readObject();
            if (path.equals("Stores"))
                database.stores = (ArrayList<Store>) objectInputStream.readObject();
            if (path.equals("Carts"))
                database.shoppingCarts = (ArrayList<ShoppingCart>) objectInputStream.readObject();
            if (path.equals("Transactions"))
                database.transactions = (ArrayList<Transaction>) objectInputStream.readObject();
            if (path.equals("Vouchers"))
                database.voucherCards = (ArrayList<VoucherCard>) objectInputStream.readObject();
            if(path.equals("Suggests"))
                database.suggests=(ArrayList<Suggest>)objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        }
    }



    public ArrayList<Store> getStores() {
        return stores;
    }

    public ArrayList<Brand> getBrands() {
        return brands;
    }

    public void setBrands(ArrayList<Brand> brands) {
        this.brands = brands;
    }

}
