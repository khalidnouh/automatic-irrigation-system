//package digtalfactory.irrigation.system.controller;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/irrigationController")
//public class IrrigationController {
//    @PreAuthorize("hasAnyAuthority('"+ HakeemRoles.VIEW_PAYMENT_CARD_PATIENT +"')")
//    @RequestMapping(value = "/findUserCardsList", method = RequestMethod.GET)
//    public ResponseEntity<?> findUserCardsList() throws NotFoundException {
//        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        return new ResponseEntity<>(cardService.list(principal), HttpStatus.OK);
//    }
//
//}
