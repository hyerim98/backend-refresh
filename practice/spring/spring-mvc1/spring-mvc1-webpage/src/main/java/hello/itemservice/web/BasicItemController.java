package hello.itemservice.web;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {
    private final ItemRepository itemRepository;

    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 30000, 15));
    }

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    /*@PostMapping("/add")
    public String addItemV1(@ModelAttribute Item item, Model model) {
        itemRepository.save(item);
        model.addAttribute("item", item);
        return "basic/item"; // 새로고침 시 상품이 중복으로 등록되는 문제 발생
    }*/

    @PostMapping("/add")
    public String addItemV2(@ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        Item saveItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", saveItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}"; // redirect로 새로고침 시 상품 중복 등록 문제 해결(PRG)
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        model.addAttribute("item", itemRepository.findById(itemId));
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, @ModelAttribute Item item, Model model) {
        Item findItem = itemRepository.findById(itemId);
        findItem.setItemName(item.getItemName());
        findItem.setPrice(item.getPrice());
        findItem.setQuantity(item.getQuantity());
        model.addAttribute("item", item);
        return "basic/item";
    }
}
