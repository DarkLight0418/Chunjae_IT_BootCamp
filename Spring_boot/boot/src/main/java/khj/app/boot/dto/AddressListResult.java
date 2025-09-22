package khj.app.boot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import khj.app.boot.domain.Address;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressListResult {
    private Page<Address> list;
    private int page;
    private int size;
    private long totalCount;
    private long totalPageCount;

    /*
    public AddressListResult(Page<Address> list, int page, int size, long totalCount){
        this.list = list;
        this.page = page;
        this.size = size;
        this.totalCount = totalCount;
        this.totalPageCount = calTotalPageCount();
    }
    private long calTotalPageCount(){
        long tpc = totalCount/size;
        if(totalCount%size !=0) tpc++;
        return tpc;
    }*/
}
