package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Account;
import com.example.demo.model.Book;
import com.example.demo.model.Borrow;
import com.example.demo.service.AccountService;
import com.example.demo.service.BookService;
import com.example.demo.service.BorrowService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
    
    @Autowired
    private AccountService accService;
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private BorrowService borrowService;
    
    public Account user;

    @GetMapping("/")
    public String getLogin(Model model) {
        model.addAttribute("account", new Account());
        
        return "login"; 
    }
    
    @GetMapping("/logout/{id}")
    public String Logout(Model model) {
    	System.out.println(user.getName());
    	user = null;
    	return "redirect:/";
    }
    
    
    
    @PostMapping("/checkLogin")
    public String checkLogin(@ModelAttribute Account accountLogin, Model model) {
        Account account = accService.getAccountByEmail(accountLogin.getEmailAddress());
        if (account != null && account.getPassword().equals(accountLogin.getPassword())) {
            user = account;
            user.setAdmin(accService.isAdmin(accountLogin.getEmailAddress())); // Set isAdmin flag
            if (user.isAdmin()) { // เช็คว่าเป็นแอดมินหรือเปล่าถ้าเป็นแอดมินให้ส่งไปที่หน้าAdminList
                System.out.println(user.getId());
                return "redirect:/adminList";  
            } else {
                return "redirect:/allBook"; //ถ้าเป็นยูสปกติให้ส่งไปที่หน้าAllBook
            }
        } else {
            return "login";  
        }
    }
    

    
    @GetMapping("/allBook")
    public String getAllBook(Model model) {
    	if(user == null) {
    		return "redirect:/";
    	}
    	Account user1 = accService.getAccountById(user.getId());
    	
        model.addAttribute("listBook", bookService.getBook());
        model.addAttribute("user",user1);
        System.out.println(user1.getId());
        return "allBook";
    }
    

    @GetMapping("/register")
    public String registerAccount(Model model) {
        model.addAttribute("account", new Account());
        return "register";
    }
    
    @PostMapping("/createAccount")
    public String createAccount(@ModelAttribute Account accountRequest, Model model) {
        accService.addAccount(accountRequest);
        return "redirect:/allBook"; 
    }
    
	@PostMapping("/createBooks")
	public String createBook(@ModelAttribute Book book) {
		bookService.addBook(book);
		return "redirect:/Add_RemoveBooks";
	}

    @GetMapping("/bookDetail")
    public String getBookDetail(Model model) {
        // You may need to add attributes to model if necessary
        return "bookDetail";
    }
    
    @GetMapping("/adminList")
    public String adminList(Model model) {
    
    	if (user == null) {
    		return "redirect:/";
    	}
    	
    	if (!user.isAdmin()) { // ตรวจสอบว่ายูสเซอร์เป็นแอดมินหรือเปล่า
            return "redirect:/"; // ไม่เป็นจะสส่งกลับไปที่หน้าแรก
        }
        
        // Admin-only logic here
    	List<Borrow> listBook = borrowService.getBook();
    	model.addAttribute("listBooks", listBook);
    	
        return "adminList";
    }
    
    @GetMapping("/book-details/{id}")
    public String getBookById(@PathVariable("id") long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "bookDetail";
    }
    
    @GetMapping("/AdminBookDetail")
    public String getAdminBookDetail(Model model) {
    	if (user == null) {
    		return "redirect:/";
    	}
    	
    	if (!user.isAdmin()) { // ตรวจสอบว่ายูสเซอร์เป็นแอดมินหรือเปล่า
            return "redirect:/"; // ไม่เป็นจะสส่งกลับไปที่หน้าแรก
        }
    	return "adminBookDetail";
    }
    
    @GetMapping("/addDeleteBook")
    public String getAddDeleteBook(Model model) {
    	if (user == null) {
    		return "redirect:/Add_RemoveBooks";
    	}
    	
    	if (!user.isAdmin()) { // ตรวจสอบว่ายูสเซอร์เป็นแอดมินหรือเปล่า
            return "redirect:/"; // ไม่เป็นจะสส่งกลับไปที่หน้าแรก
        }
       
    	model.addAttribute("book", new Book());
    	return "addDeleteBook";
    }
    
    @GetMapping("/Add_RemoveBooks")
    public String getAdd_RemoveBooks(Model model) {
    	if (user == null) {
    		return "redirect:/";
    	}
    	
    	model.addAttribute("listBook", bookService.getBook());
    	return "Add_RemoveBooks";
    }
    
    
    @GetMapping("/editBook/{id}")
    public String edit_book(@PathVariable("id") Long id,Model model) {
    	if (user == null) {
    		return "redirect:/Add_RemoveBooks";
    	}
    	
    	Book book = bookService.getBookById(id);
    	model.addAttribute("book",book);
	
    	return "edit_book";
    }
    
    @PostMapping("/editBook/{id}")
    public String updateBook(@PathVariable("id") long id, @ModelAttribute Book book) {
        bookService.updatebook(id, book);
        return "redirect:/adminList";
    }
    
    
    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable long id) {
        bookService.deleteByid(id);
        return "redirect:/Add_RemoveBooks";
    }

    @PostMapping("/borrowbook/{id}")
    public String borrowBook(@ModelAttribute Book book) {
    	Book existingBook = bookService.getBookById(book.getBid());
    	if (existingBook != null) {
    	    existingBook.setAvailability(false);
    	    borrowService.addBorrow(new Borrow(LocalDate.now(), LocalDate.now().plusDays(7), existingBook, user));
    	}
    	return "redirect:/allBook";
    }
    
    @GetMapping("/Borrow")
    public String getBorrow(Model model) {
    	model.addAttribute("borrow",borrowService.getBook());
    	return "borrow";
    }
    
    @PostMapping("/ConfirmReturn/Book/{id}")
    public String confirmBorrow(@ModelAttribute Borrow borrow,Model model) {
    	
    	Borrow existingBorrow = borrowService.getBorrowById(borrow.getBR_id());
    	if(existingBorrow != null) {
    		existingBorrow.getBook().setAvailability(true);
    		borrowService.deleteByid(borrow.getBR_id());
    	}
    	return "redirect:/adminList";
    }
    
    
    
    
}
