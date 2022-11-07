package shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShopController {

	@RequestMapping("/index_shop.do")
	public String index_shop() {
		return "admin/main";
	}
}
