package ru.zharko.sberlib.dto.mapKeys;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookTitleAuthorMapKey {
    private String bookTitle;
    private String bookAuthor;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        BookTitleAuthorMapKey that  = (BookTitleAuthorMapKey) obj;

        return (this.bookTitle.equals(that .bookTitle) && this.bookAuthor.equals(that.bookAuthor));
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookTitle, bookAuthor);
    }
}
