package hello.upload.controller;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

// 상품 저장용 폼
@Data
public class ItemForm {
    private Long itemId;
    private String itemName;
    private List<MultipartFile> imageFiles; // 이미지 다중 업로드
    private MultipartFile attachFile; // @ModelAttribte에서 사용
}
