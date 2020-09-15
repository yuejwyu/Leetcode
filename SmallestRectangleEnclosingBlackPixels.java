public class Solution {
  public int minArea(int[][] image, int x, int y) {
    // Write your solution here
    if (image == null || image.length == 0 || image[0].length == 0) {
      return 0;
    }
    int left = searchColumns(image, 0, y, true);
    int right = searchColumns(image, y, image[0].length - 1, false);
    int top = searchRows(image, 0, x, true);
    int bottom = searchRows(image, x, image.length - 1, false);
    return (right - left + 1) * (bottom - top + 1);
  }

  private int searchColumns(int[][] image, int left, int right, boolean isLeftPart) {
    while (left < right - 1) {
      int mid = left + (right - left) / 2;
      if (searchColumn(image, mid)) {
        if (isLeftPart) {
          right = mid;
        } else {
          left = mid;
        }
      } else {
        if (isLeftPart) {
          left = mid + 1;
        } else {
          right = mid - 1;
        }
      }
    }
    if (isLeftPart) {
      return searchColumn(image, left) ? left : right;
    } else {
      return searchColumn(image, right) ? right : left;
    }
  }

  private int searchRows(int[][] image, int top, int bottom, boolean isTopPart) {
    while (top < bottom - 1) {
      int mid = top + (bottom - top) / 2;
      if (searchRow(image, mid)) {
        if (isTopPart) {
          bottom = mid;
        } else {
          top = mid;
        }
      } else {
        if (isTopPart) {
          top = mid + 1;
        } else {
          bottom = mid - 1;
        }
      }
    }
    if (isTopPart) {
      return searchRow(image, top) ? top : bottom;
    } else {
      return searchRow(image, bottom) ? bottom : top;
    }
  }

  private boolean searchColumn(int[][] image, int col) {
    for (int i = 0; i < image.length; i++) {
      if (image[i][col] == 1) return true;
    }
    return false;
  }

  private boolean searchRow(int[][] image, int row) {
    for (int i = 0; i < image[0].length; i++) {
      if (image[row][i] == 1) return true;
    }
    return false;
  }
}
