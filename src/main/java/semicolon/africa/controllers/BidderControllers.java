package semicolon.africa.controllers;
import org.springframework.web.bind.annotation.*;
import semicolon.africa.data.models.Product;
import semicolon.africa.dtos.reposonse.RegisterResponse;
import semicolon.africa.dtos.request.RegisterDto;
import semicolon.africa.service.imp.BidderServiceImpl;
import semicolon.africa.service.BidderService;

@RestController
@RequestMapping("/api")
public class BidderControllers {

    private final BidderService bidderService;

    public BidderControllers(BidderServiceImpl bidderService) {
        this.bidderService = bidderService;
    }

    @PostMapping("/register/bidder")
    public RegisterResponse registerBidder(@RequestBody RegisterDto request) {
        return bidderService.register(request);
    }

    @PostMapping("/bid")
    public void bid(@RequestBody String userId, @RequestBody Product product){
        bidderService.bid(userId, product);
    }
//
//    @GetMapping("/viewAllProducts")
//    public List<Product> viewAllProducts(){
//        return
//    }
}
