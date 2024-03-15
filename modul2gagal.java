import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class LoginManager {
    private Map<String, String> mahasiswaCredentials;
    private Map<String, User> adminCredentials;

    public LoginManager() {
        mahasiswaCredentials = new HashMap<>();

        mahasiswaCredentials.put("202310370311317", "317");

        adminCredentials = new HashMap<>();

        adminCredentials.put("1", new User("1", "admin123"));
    }

    public boolean loginMahasiswa(String nim, String password) {
        if (mahasiswaCredentials.containsKey(nim)) {
            String storedPassword = mahasiswaCredentials.get(nim);
            return password.equals(storedPassword) && nim.length() == 15;
        }
        return false;
    }

    public boolean loginAdmin(String username, String password) {
        if (adminCredentials.containsKey(username)) {
            User user = adminCredentials.get(username);
            return password.equals(user.getPassword());
        }
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        LoginManager loginManager = new LoginManager();
        Scanner scanner = new Scanner(System.in);
        boolean isLoggedIn = false;

        while (!isLoggedIn) {
            System.out.println(
                    " ===== Library System =====\n 1. Login as Student\n 2. Login as Admin\n 3. exit\n choose option(1-3): ");
            int mode = scanner.nextInt();
            scanner.nextLine();

            switch (mode) {
                case 1:
                    System.out.println("Masukkan NIM: ");
                    String nim = scanner.nextLine();
                    System.out.println("Masukkan password: ");
                    String mhsPassword = scanner.nextLine();
                    isLoggedIn = loginManager.loginMahasiswa(nim, mhsPassword);
                    if (isLoggedIn) {
                        System.out.println("Selamat datang, mahasiswa!");
                    } else {
                        System.out.println("Login gagal. NIM atau password salah.");
                    }
                    break;
                case 2:
                    System.out.println("Masukkan username: ");
                    String username = scanner.nextLine();
                    System.out.println("Masukkan password: ");
                    String adminPassword = scanner.nextLine();
                    isLoggedIn = loginManager.loginAdmin(username, adminPassword);
                    if (isLoggedIn) {
                        System.out.println("Selamat datang, admin!");
                    } else {
                        System.out.println("Login gagal. Username atau password salah.");
                    }
                    break;
                case 3:
                    System.out.println("Terima kasih telah menggunakan sistem kami.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Mode login tidak valid. Silakan coba lagi.");
            }
        }
    }
}