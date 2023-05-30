package digtalfactory.irrigation.system.aop;

import digtalfactory.irrigation.system.dto.response.ApiResponse;
import digtalfactory.irrigation.system.exception.BadRequestException;
import digtalfactory.irrigation.system.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(value = ObjectNotFoundException.class)
    public ResponseEntity<?> handleObjectNotFoundException(ObjectNotFoundException e) {
        ApiResponse errorRespone = new ApiResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
        return new ResponseEntity<>(errorRespone, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException e) {
        ApiResponse errorRespone = new ApiResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
        return new ResponseEntity<>(errorRespone, HttpStatus.BAD_REQUEST);
    }

}

//	@Autowired
//    private HttpServletRequest request;
//
//	private Locale locale = new Locale("en");
//
//
//
//	private String errorMsg(String errorMsg, String exceptionMsg) {
//		return errorMsg + " (" + exceptionMsg + ")";
//	}
//
//
//
//	@SuppressWarnings("rawtypes")
//	@ExceptionHandler(value = InternalAuthenticationServiceException.class)
//    public ResponseEntity<ApiResponse> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException e) {
//		setLocale();
//		ApiResponse errorRespone = new ApiResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
//        return new ResponseEntity<>(errorRespone, HttpStatus.BAD_REQUEST);
//	}
//
//	@SuppressWarnings("rawtypes")
//	@ExceptionHandler(value = ParseException.class)
//    public ResponseEntity<ApiResponse> handleParseException(ParseException e) {
//		setLocale();
//		ApiResponse errorRespone = new ApiResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
//        return new ResponseEntity<>(errorRespone, HttpStatus.BAD_REQUEST);
//	}
//
//	@SuppressWarnings("rawtypes")
//	@ExceptionHandler(value = IllegalArgumentException.class)
//    public ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException e) {
//		setLocale();
//		ApiResponse errorRespone = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
//        return new ResponseEntity<>(errorRespone, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//
//	@SuppressWarnings("rawtypes")
//	@ExceptionHandler(value = NullPointerException.class)
//    public ResponseEntity<ApiResponse> handleNullPointerException(NullPointerException e) {
//		e.printStackTrace();
//		setLocale();
//		ApiResponse errorRespone = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
//        return new ResponseEntity<>(errorRespone, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//
//
//	@SuppressWarnings("rawtypes")
//	@ExceptionHandler(value = ChangeSetPersister.NotFoundException.class)
//    public ResponseEntity<ApiResponse> handleGenericNotFoundException(NotFoundException e) {
//		e.printStackTrace();
//		setLocale();
//		ApiResponse errorRespone = new ApiResponse(
//				HttpStatus.NOT_FOUND.value(),
////				errorMsg(ResponseMessage.RECORD_NOT_FOUND.getValue(locale), e.getMessage()),
//				ResponseMessage.RECORD_NOT_FOUND.getValue(locale),
//				null);
//        return new ResponseEntity<>(errorRespone, HttpStatus.NOT_FOUND);
//	}
//
//	@SuppressWarnings("rawtypes")
//	@ExceptionHandler(value = DuplicateKeyException.class)
//    public ResponseEntity<ApiResponse> handleDuplicateKeyException(DuplicateKeyException e) {
//		setLocale();
//		ApiResponse errorRespone = new ApiResponse(
//				HttpStatus.CONFLICT.value(),
////				errorMsg(ResponseMessage.DUPLICATE_INFORMATION.getValue(locale), e.getMessage()),
//				ResponseMessage.DUPLICATE_INFORMATION.getValue(locale),
//				null);
//        return new ResponseEntity<>(errorRespone, HttpStatus.CONFLICT);
//	}
//
////	@SuppressWarnings("rawtypes")
////	@ExceptionHandler(value = UsernameNotFoundException.class)
////	public ResponseEntity<ApiResponse> handleUsernameNotFoundException(UsernameNotFoundException e){
////		setLocale();
////		ApiResponse errorResponse = new ApiResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), null);
////		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
////	}
//
//	@SuppressWarnings("rawtypes")
//	@ExceptionHandler(value = DisabledUserException.class)
//	public ResponseEntity<ApiResponse> handleDisabledUserException(DisabledUserException e){
//		setLocale();
//		ApiResponse errorResponse = new ApiResponse(
//				HttpStatus.UNAUTHORIZED.value(),
////				errorMsg(ResponseMessage.DISABLED_USER.getValue(locale), e.getMessage()),
//				ResponseMessage.DISABLED_USER.getValue(locale),
//				null);
//		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
//	}
//
//	@SuppressWarnings("rawtypes")
//	@ExceptionHandler(value = ExpiredOtpException.class)
//	public ResponseEntity<ApiResponse> handleExpiredOtpException(ExpiredOtpException e){
//		setLocale();
//		ApiResponse errorResponse = new ApiResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), null);
//		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
//	}
//
//	@SuppressWarnings("rawtypes")
//	@ExceptionHandler(value = ShippingAddressMissingException.class)
//	public ResponseEntity<ApiResponse> handleShippingAddressMissingException(ShippingAddressMissingException e) {
//		setLocale();
//		ApiResponse errorResponse = new ApiResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
//		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//	}
//
//	@SuppressWarnings("rawtypes")
//	@ExceptionHandler(value = InvalidVerificationException.class)
//	public ResponseEntity<ApiResponse> handleInvalidVerificationException(InvalidVerificationException e){
//		setLocale();
//		ApiResponse errorResponse = new ApiResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), null);
//		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
//	}
//
//	@SuppressWarnings("rawtypes")
//	@ExceptionHandler(value = InvalidPasswordException.class)
//	public ResponseEntity<ApiResponse> handleInvalidPasswordException(InvalidPasswordException e){
//		setLocale();
//		ApiResponse errorResponse = new ApiResponse(HttpStatus.BAD_REQUEST.value(), ResponseMessage.INVALID_PASSWORD.getValue(locale), null);
//		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//	}
//
//	@SuppressWarnings("rawtypes")
//	@ExceptionHandler(value = EntityNotFoundException.class)
//	public ResponseEntity<ApiResponse> handleEntityNotFoundException(EntityNotFoundException e){
//		setLocale();
//		ApiResponse errorResponse = new ApiResponse(
//				HttpStatus.BAD_REQUEST.value(),
////				errorMsg(ResponseMessage.ENTITY_NOT_FOUND.getValue(locale), e.getMessage()),
//				ResponseMessage.ENTITY_NOT_FOUND.getValue(locale),
//				null);
//		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//	}
//
//	@SuppressWarnings("rawtypes")
//	@ExceptionHandler(value = UnprocessableEntityException.class)
//	public ResponseEntity<ApiResponse> handleUnprocessableEntityException(UnprocessableEntityException e){
//		setLocale();
//		ApiResponse errorResponse = new ApiResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage(), null);
//		return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
//	}
//
//	@SuppressWarnings("rawtypes")
//	//@ExceptionHandler(value = AuthenticationException.class)
//	@ExceptionHandler(value = {AuthenticationException.class, DisabledException.class, LockedException.class, BadCredentialsException.class} )
//	public ResponseEntity<ApiResponse> handleAuthenticationException(Exception e){
//		setLocale();
//		ApiResponse errorResponse = new ApiResponse(
//				HttpStatus.UNAUTHORIZED.value(),
//				ResponseMessage.INVALID_CREDENTIALS.getValue(locale),
//				null);
//
//		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
//	}
//
//	@SuppressWarnings("rawtypes")
//	@ExceptionHandler(value = UnverifiedUserException.class)
//	public ResponseEntity<ApiResponse> handleUnverifiedUserException(UnverifiedUserException e){
//		setLocale();
//		ApiResponse errorResponse = new ApiResponse(HttpStatus.UNAUTHORIZED.value(), ResponseMessage.ACCOUNT_NOT_VERIFIED.getValue(locale), null);
//		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
//	}
//
//	@SuppressWarnings("rawtypes")
//	@ExceptionHandler(value = InvalidTokenException.class)
//	public ResponseEntity<ApiResponse> handleInvalidToken(InvalidTokenException e){
//		setLocale();
//		ApiResponse errorResponse = new ApiResponse(HttpStatus.UNAUTHORIZED.value(), ResponseMessage.INVALID_TOKEN.getValue(locale), null);
//		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
//	}
//
//	@SuppressWarnings("rawtypes")
//	@ExceptionHandler(value = ApiException.class)
//	public ResponseEntity<ApiResponse> handleApiException(ApiException e){
//		setLocale();
//		ApiResponse errorResponse = new ApiResponse(HttpStatus.BAD_REQUEST.value(), ResponseMessage.PHONE_NUMBER_NOT_VALID.getValue(locale), null);
//		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//	}
//
//	@SuppressWarnings("rawtypes")
//	@ExceptionHandler(value = InterruptedException.class)
//	public ResponseEntity<ApiResponse> handleInterruptedException(InterruptedException e){
//		setLocale();
//		ApiResponse errorResponse = new ApiResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
//		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//	}
//
//	@SuppressWarnings("rawtypes")
//	@ExceptionHandler(value = IOException.class)
//	public ResponseEntity<ApiResponse> handleIOException(IOException e){
//		setLocale();
//		ApiResponse errorResponse = new ApiResponse(HttpStatus.BAD_REQUEST.value(), ResponseMessage.WRONG_INPUT.getValue(locale), null);
//		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//	}
//
//	@SuppressWarnings("rawtypes")
//	@ExceptionHandler(value = HibernateException.class)
//	public ResponseEntity<ApiResponse> handleHibernateException(HibernateException e){
//		e.printStackTrace();
//		setLocale();
//		ApiResponse errorResponse = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
//		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//
////	@SuppressWarnings("rawtypes")
////	@ExceptionHandler(value = FirebaseMessagingException.class)
////	public ResponseEntity<ApiResponse> handleFirebaseMessagingException(FirebaseMessagingException e) {
////		setLocale();
////		ApiResponse errorResponse = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
////		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
////	}
//
////	@SuppressWarnings("rawtypes")
////	@ExceptionHandler(value = BadRequest.class)
////	public ResponseEntity<ApiResponse> handleBadRequestException(BadRequest e) {
////		setLocale();
////		ApiResponse errorResponse = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
////		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
////	}
//
//
//	@SuppressWarnings("rawtypes")
//	@ExceptionHandler(value = ErrorResponseException.class)
//    public ResponseEntity<ApiResponse> handleErrorResponseException(ErrorResponseException e) {
//		setLocale();
//		ApiResponse errorResponse = new ApiResponse(HttpStatus.NOT_FOUND.value(), ResponseMessage.PHONE_NUMBER_NOT_VALID.getValue(locale), null);
//		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
//	}
//
//	@SuppressWarnings("rawtypes")
//	@ExceptionHandler(value = PromoCodeNotFoundException.class)
//    public ResponseEntity<ApiResponse> handlePromoCodeNotFoundException(PromoCodeNotFoundException e) {
//		setLocale();
//		ApiResponse errorResponse = new ApiResponse(HttpStatus.NOT_FOUND.value(), ResponseMessage.PROMOCODE_NOT_FOUND.getValue(locale), null);
//		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
//	}
//
//	@SuppressWarnings("rawtypes")
//	@ExceptionHandler(value = ConstraintViolationException.class)
//	public ResponseEntity<ApiResponse> handleConstraintViolationException(ConstraintViolationException e){
//		setLocale();
//		Set<ConstraintViolation<?>> violationSet = e.getConstraintViolations();
//
//		StringBuilder errorMsg = new StringBuilder();
//
//		for(ConstraintViolation<?> violation : violationSet) {
//			errorMsg.append("\n");
//			errorMsg.append("Field (");
//			errorMsg.append(violation.getPropertyPath());
//			errorMsg.append(") : ");
//
//			errorMsg.append(violation.getMessage());
//		}
//
//		ApiResponse errorResponse = new ApiResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), errorMsg.toString(), null);
//		return new ResponseEntity<> (errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
//	}
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
