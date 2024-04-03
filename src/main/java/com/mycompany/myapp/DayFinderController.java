
// Main Webservice Endpoint
@RestController
@RequestMapping("/services/calendar")
public class DayFinderController {

    @Autowired
    private HistoryService historyService;

    @GetMapping("/dayfinder")
    public ResponseEntity<Map<String, String>> findDayOfWeek(@RequestParam String date) {
        String dayOfWeek = calculateDayOfWeek(date);
        historyService.saveSearch(date, dayOfWeek);
        Map<String, String> response = new HashMap<>();
        response.put("date", date);
        response.put("dayOfWeek", dayOfWeek);
        return ResponseEntity.ok(response);
    }

    private String calculateDayOfWeek(String date) {
        // Implement logic to calculate the day of the week
        return "Monday"; // Placeholder for actual calculation
    }
}

// Historical Microservice
@RestController
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @GetMapping("/all")
    public ResponseEntity<List<SearchHistory>> getAllSearchHistory() {
        List<SearchHistory> history = historyService.getAllSearchHistory();
        return ResponseEntity.ok(history);
    }
}

// Service to Manage Search History
@Service
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    public void saveSearch(String date, String dayOfWeek) {
        // Save search date and response in the database
    }

    public List<SearchHistory> getAllSearchHistory() {
        // Retrieve all search history from the database
        return historyRepository.findAll();
    }
}

// Entity for Search History
@Entity
public class SearchHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String searchDate;

    @ElementCollection
    private Map<String, String> searchItems;

    // Getters and Setters
}
