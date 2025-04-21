package semicolon.africa.controllers;
import org.springframework.web.bind.annotation.*;
import semicolon.africa.data.models.Bidder;
import semicolon.africa.data.models.Product;
import semicolon.africa.service.BidderImpl;
import semicolon.africa.service.BidderService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BidderControllers {

    private final BidderService bidderService;

    public BidderControllers(BidderImpl bidderService) {
        this.bidderService = bidderService;
    }

    @PostMapping("/register/bidder")
    public void registerBidder(@RequestBody Bidder bidder){
        bidderService.register(bidder);
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
