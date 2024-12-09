// borrowing-service/src/main/java/com/example/borrowingservice/query/projection/BorrowingProjection.java

package com.example.borrowingservice.query.projection;

import com.example.borrowingservice.command.data.Borrowing;
import com.example.borrowingservice.command.data.BorrowingRepository;
import com.example.borrowingservice.command.model.BorrowingRequestModel;
import com.example.borrowingservice.query.queries.GetAllBorrowingQuery;
import com.example.borrowingservice.query.queries.GetBorrowingDetailQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class BorrowingProjection {
    private final BorrowingRepository borrowingRepository;

    @QueryHandler
    public List<BorrowingRequestModel> handle(GetAllBorrowingQuery query) {
        List<Borrowing> borrowings = borrowingRepository.findAll();
        List<BorrowingRequestModel> result = borrowings.stream().map(borrowing -> {
            BorrowingRequestModel borrowingRequestModel = new BorrowingRequestModel();
            BeanUtils.copyProperties(borrowing, borrowingRequestModel);
            return borrowingRequestModel;
        }).toList();

        return result;
    }

    @QueryHandler
    public BorrowingRequestModel handle(GetBorrowingDetailQuery query) throws Exception {
        BorrowingRequestModel borrowingRequestModel = new BorrowingRequestModel();
        Borrowing borrowings = borrowingRepository.findById(query.getId()).orElseThrow(() -> new Exception("Borrowing not found id: " + query.getId()));
        BeanUtils.copyProperties(borrowings, borrowingRequestModel);
        return borrowingRequestModel;
    }
}