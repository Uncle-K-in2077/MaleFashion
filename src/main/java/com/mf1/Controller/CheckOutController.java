package com.mf1.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mf1.dto.CartElement;
import com.mf1.dto.PhoneValidator;
import com.mf1.entities.Account;
import com.mf1.entities.Order;
import com.mf1.entities.OrderDetail;
import com.mf1.entities.Product;
import com.mf1.repository.OrderRepository;
import com.mf1.repository.ProductRepository;
import com.mf1.repository.orderDetailRepository;
import com.mf1.service.AccountService;
import com.mf1.service.CartService;
import com.mf1.service.MailerService;
import com.mf1.service.SessionService;

@Controller
@RequestMapping("/checkOut")
public class CheckOutController {
	@Autowired
	CartService cartService;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	orderDetailRepository orderDetailRepository;
	
	@Autowired
	MailerService mailerService;
	
	@Autowired
	AccountService accountService;
	
	private final PhoneValidator phoneValidator;

    @Autowired
    public CheckOutController(PhoneValidator phoneValidator) {
        this.phoneValidator = phoneValidator;
    }
	
	
	@GetMapping()
	public String checkOutView(Model model) {
		List<CartElement> cartItems = cartService.getList();
		int itemCount = cartService.getCount();
		double totalAmount = cartService.getAmount();
		Account account = sessionService.get("account");
		sessionService.set("account", account);
		sessionService.set("cartItems", cartItems);
		sessionService.set("itemCount", itemCount);
		sessionService.set("totalAmount", totalAmount);
		model.addAttribute("currentPage", "shop");
		
		Order order = new Order();
		model.addAttribute("order", order);
		return "shoppingCart-checkOut";
	}
	
	@PostMapping()
	public String create(Model model,@RequestParam(value = "order_note", defaultValue = "") String note,
			@Valid @ModelAttribute("order") Order order,
            BindingResult bindingResult) {
		
        // Kiểm tra lỗi
        if (bindingResult.hasErrors()) {
            sessionService.set("CheckOutMessage", "Invalid phone number");
            System.out.println("Lỗi Validate SDT");
            model.addAttribute("order", order);
            return "shoppingCart-checkOut";
        }
	    
	   
		try {
			Account account = sessionService.get("account");
//			Order order = new Order();
			order.setAccount(account);
			order.setCreatedAt(new Date());
			order.setNote(note);
			order.setAmount(cartService.getAmount());
			order.setStatus(1);
			order.setPhone(order.getPhone());
			order.setAddress(order.getAddress());
			Order newOrder = orderRepository.save(order);

			List<CartElement> listCartElement = cartService.getList();
			List<OrderDetail> listOrderDetail = new ArrayList<>();
			if (listCartElement.size() > 0) {
				for (CartElement item : listCartElement) {
					OrderDetail o = new OrderDetail();
					Product product = productRepository.findById(item.getId()).get();

					o.setProduct(product);
					o.setOrder(newOrder);
					o.setQuantity(item.getQuantity());
					o.setPrice(product.getPrice());
					o.setStatus(1);
					listOrderDetail.add(o);
				}
				orderDetailRepository.saveAll(listOrderDetail);
			}
			mailerService.send(account.getEmail(), "Male-Fashion" , "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n"
					+ "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" style=\"font-family:'trebuchet ms', 'lucida grande', 'lucida sans unicode', 'lucida sans', tahoma, sans-serif\">\r\n"
					+ "<head>\r\n"
					+ "<meta charset=\"UTF-8\">\r\n"
					+ "<meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\r\n"
					+ "<meta name=\"x-apple-disable-message-reformatting\">\r\n"
					+ "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
					+ "<meta content=\"telephone=no\" name=\"format-detection\">\r\n"
					+ "<title>New Template</title><!--[if (mso 16)]>\r\n"
					+ "<style type=\"text/css\">\r\n"
					+ "a {text-decoration: none;}\r\n"
					+ "</style>\r\n"
					+ "<![endif]--><!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]--><!--[if gte mso 9]>\r\n"
					+ "<xml>\r\n"
					+ "<o:OfficeDocumentSettings>\r\n"
					+ "<o:AllowPNG></o:AllowPNG>\r\n"
					+ "<o:PixelsPerInch>96</o:PixelsPerInch>\r\n"
					+ "</o:OfficeDocumentSettings>\r\n"
					+ "</xml>\r\n"
					+ "<![endif]--><!--[if !mso]><!-- -->\r\n"
					+ "<link href=\"https://fonts.googleapis.com/css2?family=Alex+Brush&display=swap\" rel=\"stylesheet\"><!--<![endif]-->\r\n"
					+ "<style type=\"text/css\">\r\n"
					+ "#outlook a {\r\n"
					+ "padding:0;\r\n"
					+ "}\r\n"
					+ ".es-button {\r\n"
					+ "mso-style-priority:100!important;\r\n"
					+ "text-decoration:none!important;\r\n"
					+ "}\r\n"
					+ "a[x-apple-data-detectors] {\r\n"
					+ "color:inherit!important;\r\n"
					+ "text-decoration:none!important;\r\n"
					+ "font-size:inherit!important;\r\n"
					+ "font-family:inherit!important;\r\n"
					+ "font-weight:inherit!important;\r\n"
					+ "line-height:inherit!important;\r\n"
					+ "}\r\n"
					+ ".es-desk-hidden {\r\n"
					+ "display:none;\r\n"
					+ "float:left;\r\n"
					+ "overflow:hidden;\r\n"
					+ "width:0;\r\n"
					+ "max-height:0;\r\n"
					+ "line-height:0;\r\n"
					+ "mso-hide:all;\r\n"
					+ "}\r\n"
					+ "@media only screen and (max-width:600px) {p, ul li, ol li, a { line-height:150%!important } h1, h2, h3, h1 a, h2 a, h3 a { line-height:120% } h1 { font-size:30px!important; text-align:center } h2 { font-size:26px!important; text-align:center } h3 { font-size:20px!important; text-align:left } .es-header-body h1 a, .es-content-body h1 a, .es-footer-body h1 a { font-size:30px!important } .es-header-body h2 a, .es-content-body h2 a, .es-footer-body h2 a { font-size:26px!important } .es-header-body h3 a, .es-content-body h3 a, .es-footer-body h3 a { font-size:20px!important; text-align:left } .es-menu td a { font-size:14px!important } .es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body a { font-size:14px!important } .es-content-body p, .es-content-body ul li, .es-content-body ol li, .es-content-body a { font-size:14px!important } .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, .es-footer-body a { font-size:14px!important } .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a { font-size:12px!important } *[class=\"gmail-fix\"] { display:none!important } .es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3 { text-align:center!important } .es-m-txt-r, .es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3 { text-align:right!important } .es-m-txt-l, .es-m-txt-l h1, .es-m-txt-l h2, .es-m-txt-l h3 { text-align:left!important } .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img { display:inline!important } .es-button-border { display:inline-block!important } a.es-button, button.es-button { font-size:18px!important; display:inline-block!important } .es-adaptive table, .es-left, .es-right { width:100%!important } .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header { width:100%!important; max-width:600px!important } .es-adapt-td { display:block!important; width:100%!important } .adapt-img { width:100%!important; height:auto!important } .es-m-p0 { padding:0!important } .es-m-p0r { padding-right:0!important } .es-m-p0l { padding-left:0!important } .es-m-p0t { padding-top:0!important } .es-m-p0b { padding-bottom:0!important } .es-m-p20b { padding-bottom:20px!important } .es-mobile-hidden, .es-hidden { display:none!important } tr.es-desk-hidden, td.es-desk-hidden, table.es-desk-hidden { width:auto!important; overflow:visible!important; float:none!important; max-height:inherit!important; line-height:inherit!important } tr.es-desk-hidden { display:table-row!important } table.es-desk-hidden { display:table!important } td.es-desk-menu-hidden { display:table-cell!important } .es-menu td { width:1%!important } table.es-table-not-adapt, .esd-block-html table { width:auto!important } table.es-social { display:inline-block!important } table.es-social td { display:inline-block!important } .es-m-p5 { padding:5px!important } .es-m-p5t { padding-top:5px!important } .es-m-p5b { padding-bottom:5px!important } .es-m-p5r { padding-right:5px!important } .es-m-p5l { padding-left:5px!important } .es-m-p10 { padding:10px!important } .es-m-p10t { padding-top:10px!important } .es-m-p10b { padding-bottom:10px!important } .es-m-p10r { padding-right:10px!important } .es-m-p10l { padding-left:10px!important } .es-m-p15 { padding:15px!important } .es-m-p15t { padding-top:15px!important } .es-m-p15b { padding-bottom:15px!important } .es-m-p15r { padding-right:15px!important } .es-m-p15l { padding-left:15px!important } .es-m-p20 { padding:20px!important } .es-m-p20t { padding-top:20px!important } .es-m-p20r { padding-right:20px!important } .es-m-p20l { padding-left:20px!important } .es-m-p25 { padding:25px!important } .es-m-p25t { padding-top:25px!important } .es-m-p25b { padding-bottom:25px!important } .es-m-p25r { padding-right:25px!important } .es-m-p25l { padding-left:25px!important } .es-m-p30 { padding:30px!important } .es-m-p30t { padding-top:30px!important } .es-m-p30b { padding-bottom:30px!important } .es-m-p30r { padding-right:30px!important } .es-m-p30l { padding-left:30px!important } .es-m-p35 { padding:35px!important } .es-m-p35t { padding-top:35px!important } .es-m-p35b { padding-bottom:35px!important } .es-m-p35r { padding-right:35px!important } .es-m-p35l { padding-left:35px!important } .es-m-p40 { padding:40px!important } .es-m-p40t { padding-top:40px!important } .es-m-p40b { padding-bottom:40px!important } .es-m-p40r { padding-right:40px!important } .es-m-p40l { padding-left:40px!important } .es-desk-hidden { display:table-row!important; width:auto!important; overflow:visible!important; max-height:inherit!important } }\r\n"
					+ "</style>\r\n"
					+ "</head>\r\n"
					+ "<body style=\"width:100%;font-family:'trebuchet ms', 'lucida grande', 'lucida sans unicode', 'lucida sans', tahoma, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0\">\r\n"
					+ "<div class=\"es-wrapper-color\" style=\"background-color:#D6CED5\"><!--[if gte mso 9]>\r\n"
					+ "<v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">\r\n"
					+ "<v:fill type=\"tile\" color=\"#D6CED5\"></v:fill>\r\n"
					+ "</v:background>\r\n"
					+ "<![endif]-->\r\n"
					+ "<table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top;background-color:#D6CED5\">\r\n"
					+ "<tr>\r\n"
					+ "<td valign=\"top\" style=\"padding:0;Margin:0\">\r\n"
					+ "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-header\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\">\r\n"
					+ "<tr>\r\n"
					+ "<td align=\"center\" style=\"padding:0;Margin:0\">\r\n"
					+ "<table bgcolor=\"#ffffff\" class=\"es-header-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:600px\">\r\n"
					+ "<tr>\r\n"
					+ "<td class=\"es-m-p20r es-m-p20l\" align=\"left\" bgcolor=\"#e28b74\" style=\"Margin:0;padding-bottom:15px;padding-top:20px;padding-left:40px;padding-right:40px;background-color:#e28b74\"><!--[if mso]><table style=\"width:520px\" cellpadding=\"0\"\r\n"
					+ "cellspacing=\"0\"><tr><td style=\"width:156px\" valign=\"top\"><![endif]-->\r\n"
					+ "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-left\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\">\r\n"
					+ "<tr>\r\n"
					+ "<td class=\"es-m-p0r es-m-p20b\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:156px\">\r\n"
					+ "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n"
					+ "<tr>\r\n"
					+ "<td align=\"left\" style=\"padding:0;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:'trebuchet ms', 'lucida grande', 'lucida sans unicode', 'lucida sans', tahoma, sans-serif;line-height:21px;color:#333333;font-size:14px\"><br></p></td>\r\n"
					+ "</tr>\r\n"
					+ "</table></td>\r\n"
					+ "</tr>\r\n"
					+ "</table><!--[if mso]></td><td style=\"width:20px\"></td><td style=\"width:344px\" valign=\"top\"><![endif]-->\r\n"
					+ "<table cellpadding=\"0\" cellspacing=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n"
					+ "<tr>\r\n"
					+ "<td align=\"left\" style=\"padding:0;Margin:0;width:344px\">\r\n"
					+ "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n"
					+ "<tr>\r\n"
					+ "<td align=\"center\" style=\"padding:0;Margin:0;font-size:0px\"><img class=\"adapt-img\" src=\"https://xbkorc.stripocdn.email/content/guids/CABINET_67b8bdd3c75c7d2f3460add1361a2bfc9a5f7e4cbf551c21d16d0be83162ce2e/images/logo.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"309\"></td>\r\n"
					+ "</tr>\r\n"
					+ "</table></td>\r\n"
					+ "</tr>\r\n"
					+ "</table><!--[if mso]></td></tr></table><![endif]--></td>\r\n"
					+ "</tr>\r\n"
					+ "</table></td>\r\n"
					+ "</tr>\r\n"
					+ "</table>\r\n"
					+ "<table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\">\r\n"
					+ "<tr>\r\n"
					+ "<td align=\"center\" style=\"padding:0;Margin:0\">\r\n"
					+ "<table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#ffffff;width:600px\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\">\r\n"
					+ "<tr>\r\n"
					+ "<td class=\"es-m-p20t es-m-p20r es-m-p20l\" align=\"left\" style=\"padding:0;Margin:0;padding-top:40px;padding-left:40px;padding-right:40px\">\r\n"
					+ "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n"
					+ "<tr>\r\n"
					+ "<td class=\"es-m-p0r\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:520px\">\r\n"
					+ "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n"
					+ "<tr>\r\n"
					+ "<td align=\"center\" style=\"padding:0;Margin:0;font-size:0px\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:#2CB543;font-size:14px\"><img class=\"adapt-img\" src=\"https://xbkorc.stripocdn.email/content/guids/CABINET_67b8bdd3c75c7d2f3460add1361a2bfc9a5f7e4cbf551c21d16d0be83162ce2e/images/hero3.jpg\" alt=\"Very Sad\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"520\" title=\"Very Sad\"></a></td>\r\n"
					+ "</tr>\r\n"
					+ "</table></td>\r\n"
					+ "</tr>\r\n"
					+ "</table></td>\r\n"
					+ "</tr>\r\n"
					+ "<tr>\r\n"
					+ "<td class=\"es-m-p20t es-m-p20r es-m-p20l\" align=\"left\" style=\"padding:0;Margin:0;padding-top:40px;padding-left:40px;padding-right:40px\">\r\n"
					+ "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n"
					+ "<tr>\r\n"
					+ "<td class=\"es-m-p0r\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:520px\">\r\n"
					+ "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n"
					+ "<tr>\r\n"
					+ "<td align=\"center\" style=\"padding:0;Margin:0;padding-top:5px;padding-bottom:5px\"><h1 style=\"Margin:0;line-height:53px;mso-line-height-rule:exactly;font-family:'Alex Brush', cursive;font-size:44px;font-style:normal;font-weight:bold;color:#333333\">— Hello handsome boy&nbsp;—</h1></td>\r\n"
					+ "</tr>\r\n"
					+ "</table></td>\r\n"
					+ "</tr>\r\n"
					+ "<tr>\r\n"
					+ "<td class=\"es-m-p0r es-m-p20b\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:520px\">\r\n"
					+ "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#fbf5fb\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;background-color:#fbf5fb;border-radius:10px\" role=\"presentation\">\r\n"
					+ "<tr>\r\n"
					+ "<td align=\"center\" class=\"es-m-txt-c es-m-p10r es-m-p10l\" style=\"padding:0;Margin:0;padding-top:5px;padding-bottom:5px\"><h3 style=\"Margin:0;line-height:29px;mso-line-height-rule:exactly;font-family:tahoma, verdana, segoe, sans-serif;font-size:24px;font-style:normal;font-weight:normal;color:#ff5733\">Uncle K&nbsp;welcome you to the gang</h3></td>\r\n"
					+ "</tr>\r\n"
					+ "</table></td>\r\n"
					+ "</tr>\r\n"
					+ "</table></td>\r\n"
					+ "</tr>\r\n"
					+ "<tr>\r\n"
					+ "<td class=\"es-m-p10b es-m-p20r es-m-p20l\" align=\"left\" style=\"Margin:0;padding-top:10px;padding-bottom:20px;padding-left:40px;padding-right:40px\">\r\n"
					+ "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n"
					+ "<tr>\r\n"
					+ "<td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:520px\">\r\n"
					+ "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n"
					+ "<tr>\r\n"
					+ "<td align=\"left\" style=\"padding:0;Margin:0;padding-top:10px;padding-bottom:10px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:'trebuchet ms', 'lucida grande', 'lucida sans unicode', 'lucida sans', tahoma, sans-serif;line-height:23px;color:#333333;font-size:15px\">Uncle K said: Thank you for your order babeee. \nYour order total amount is "+ newOrder.getAmount() + "VND &nbsp;</p></td>\r\n"
					+ "</tr>\r\n"
					+ "</table></td>\r\n"
					+ "</tr>\r\n"
					+ "</table></td>\r\n"
					+ "</tr>\r\n"
					+ "</table></td>\r\n"
					+ "</tr>\r\n"
					+ "</table>\r\n"
					+ "<table cellpadding=\"0\" cellspacing=\"0\" class=\"es-footer\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\">\r\n"
					+ "<tr>\r\n"
					+ "<td align=\"center\" style=\"padding:0;Margin:0\">\r\n"
					+ "<table bgcolor=\"#ffffff\" class=\"es-footer-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:600px\">\r\n"
					+ "<tr>\r\n"
					+ "<td align=\"left\" bgcolor=\"#333333\" style=\"padding:10px;Margin:0;background-color:#333333;background-image:url(https://xbkorc.stripocdn.email/content/guids/CABINET_67b8bdd3c75c7d2f3460add1361a2bfc9a5f7e4cbf551c21d16d0be83162ce2e/images/hero3.jpg);background-repeat:no-repeat;background-position:left top\" background=\"https://xbkorc.stripocdn.email/content/guids/CABINET_67b8bdd3c75c7d2f3460add1361a2bfc9a5f7e4cbf551c21d16d0be83162ce2e/images/hero3.jpg\">\r\n"
					+ "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n"
					+ "<tr>\r\n"
					+ "<td align=\"left\" style=\"padding:0;Margin:0;width:580px\">\r\n"
					+ "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\">\r\n"
					+ "<tr>\r\n"
					+ "<td align=\"center\" style=\"padding:0;Margin:0;font-size:0px\"><img class=\"adapt-img\" src=\"https://xbkorc.stripocdn.email/content/guids/CABINET_67b8bdd3c75c7d2f3460add1361a2bfc9a5f7e4cbf551c21d16d0be83162ce2e/images/fpt_polytechnic_Akq.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"285\"></td>\r\n"
					+ "</tr>\r\n"
					+ "</table></td>\r\n"
					+ "</tr>\r\n"
					+ "</table></td>\r\n"
					+ "</tr>\r\n"
					+ "</table></td>\r\n"
					+ "</tr>\r\n"
					+ "</table></td>\r\n"
					+ "</tr>\r\n"
					+ "</table>\r\n"
					+ "</div>\r\n"
					+ "</body>\r\n"
					+ "</html>");
			cartService.clear();
		} catch (Exception e) {
			System.out.println("Lỗi tạo đơn hàng :" + e.getMessage());
		}

		return "redirect:/home";
		
	}
	
	@GetMapping("/{userId}")
	public String getOrdersByUserId(@PathVariable int userId, Model model) {
	    Account account = accountService.getAccountById(userId);
	    if (account == null) {
	        // Xử lý khi người dùng không tồn tại
	        return "error-page"; // Điều hướng đến trang lỗi hoặc trang thông báo không tìm thấy người dùng
	    }
	    
	    List<Order> orders = orderRepository.findByAccount(account, null);
	    
	    model.addAttribute("account", account);
	    model.addAttribute("orders", orders);
	    
	    return "user-order"; // Điều hướng đến trang hiển thị danh sách đơn hàng của người dùng
	}
	
	@GetMapping("/detail/{orderId}")
	public String getOrderById(@PathVariable int orderId, Model model) {
	    Order order = orderRepository.findById(orderId);
	    if (order == null) {
	        // Xử lý khi không tìm thấy đơn hàng
	        return "error-page"; // Điều hướng đến trang lỗi hoặc trang thông báo không tìm thấy đơn hàng
	    }
	    
	    List<OrderDetail> orderDetails = order.getOrderDetails(); // Lấy danh sách OrderDetail từ Order
	    
	    model.addAttribute("order", order);
	    model.addAttribute("orderDetails", orderDetails);
	    
	    return "user-order-detail"; // Điều hướng đến trang hiển thị chi tiết đơn hàng
	}
	
}
